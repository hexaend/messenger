package ru.hexaend.personal_chat.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hexaend.personal_chat.entity.PersonalChat;
import ru.hexaend.personal_chat.exceptions.custom.PersonalChatNotFoundException;
import ru.hexaend.personal_chat.grpc.GrpcClientService;
import ru.hexaend.personal_chat.kafka.PersonalChatKafkaService;
import ru.hexaend.personal_chat.repository.PersonalChatRepository;
import ru.hexaend.personal_chat.service.PersonalChatService;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonalChatServiceImpl implements PersonalChatService {
    private final PersonalChatRepository personalChatRepository;
    private final GrpcClientService grpcClientService;
    private final PersonalChatKafkaService personalChatKafkaService;

    @Override
    public PersonalChat getPersonalChatByChatId(UUID chatId) {
        // TODO: Custom exception
        var personalChat = personalChatRepository.findPersonalChatByIsDeletedFalseAndId(chatId).orElseThrow(() -> new RuntimeException("Personal chat not found"));
        if (personalChat.getIsDeleted()) {
            throw new PersonalChatNotFoundException();
        }
        return personalChat;
    }

    @Override
    public PersonalChat getPersonalChatByUsername(String username, String owner) {
        var personalChat = personalChatRepository.findPersonalChatByUserIdAndOwnerIdMyRealisation(username, owner).orElseThrow(() -> new RuntimeException("Personal chat not found"));
        if (personalChat.getIsDeleted()) {
            throw new PersonalChatNotFoundException();
        }
        return personalChat;
    }

    @Override
    public PersonalChat getPersonalChatByUserId(String userId, String owner) {
        String username = grpcClientService.getIdByUsername(userId);
        return getPersonalChatByUsername(username, owner);
    }

    @Override
    public PersonalChat createPersonalChatByUsername(String username, String ownerId) {
        String userId = grpcClientService.getIdByUsername(username);
        return createPersonalChatByUserId(userId, ownerId);
    }

    @Override
    public PersonalChat createPersonalChatByUserId(String userId, String ownerId) {
        Optional<PersonalChat> personalChatOptional = personalChatRepository
                .findPersonalChatByUserIdAndOwnerIdMyRealisation(userId, ownerId);
        PersonalChat personalChat;
        if (personalChatOptional.isPresent()) {
            personalChat = personalChatOptional.get();
        } else {
            personalChat = PersonalChat.builder().userId(userId).isDeleted(false).build();
            personalChatRepository.save(personalChat);
            personalChatKafkaService.personalChatCreated(personalChat);
        }

        return personalChat;
    }

    @Override
    public void deletePersonalChatByChatId(UUID chatId) {
        PersonalChat personalChat = personalChatRepository
                .findPersonalChatByIsDeletedFalseAndId(chatId)
                .orElseThrow(PersonalChatNotFoundException::new);
        personalChat.setIsDeleted(true);
        personalChatRepository.save(personalChat);
        personalChatKafkaService.personalChatDeleted(personalChat);
    }

    @Override
    public void deletePersonalChatByUsername(String username, String owner) {
        String userId = grpcClientService.getIdByUsername(username);
        deletePersonalChatByUserId(userId, owner);
    }

    @Override
    public void deletePersonalChatByUserId(String userId, String owner) {
        PersonalChat personalChat = personalChatRepository
                .findPersonalChatByUserIdAndOwnerIdMyRealisation(userId, owner)
                .orElseThrow(PersonalChatNotFoundException::new);
        personalChat.setIsDeleted(true);
        personalChatRepository.save(personalChat);
        personalChatKafkaService.personalChatDeleted(personalChat);
    }
}
