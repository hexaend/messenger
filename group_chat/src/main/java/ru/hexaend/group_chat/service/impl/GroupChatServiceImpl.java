package ru.hexaend.group_chat.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hexaend.group_chat.dto.request.AddUsersRequest;
import ru.hexaend.group_chat.dto.request.CreateGroupChatRequest;
import ru.hexaend.group_chat.dto.request.DeleteUsersRequest;
import ru.hexaend.group_chat.entity.GroupChat;
import ru.hexaend.group_chat.exceptions.custom.GroupChatNotFoundException;
import ru.hexaend.group_chat.grpc.GrpcClientService;
import ru.hexaend.group_chat.kafka.GroupChatKafkaService;
import ru.hexaend.group_chat.mapper.GroupChatMapper;
import ru.hexaend.group_chat.repository.GroupChatRepository;
import ru.hexaend.group_chat.service.GroupChatService;

import java.util.HashSet;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GroupChatServiceImpl implements GroupChatService {
    private final GroupChatMapper groupChatMapper;
    private final GroupChatRepository groupChatRepository;
    private final GroupChatKafkaService groupChatKafkaService;

    @Override
    public GroupChat createGroupChat(CreateGroupChatRequest chatRequest, String ownerId) {
        GroupChat groupChat = groupChatMapper.fromDto(chatRequest);
        groupChat.setOwnerId(ownerId);
        groupChat = groupChatRepository.save(groupChat);
        groupChatKafkaService.createGroupChat(groupChat);
        return groupChat;
    }

    @Override
    public GroupChat addUsersToGroupChat(UUID chatId, AddUsersRequest users, String userId) {
        var groupChat = this.getGroupById(chatId, userId);
        groupChat.addUsers(new HashSet<>(users.users()));
        return groupChatRepository.save(groupChat);
    }

    @Override
    public GroupChat getGroupById(UUID chatId, String userId) {
        return groupChatRepository
                .findByChatIdAndUser(chatId, userId)
                .orElseThrow(() -> new GroupChatNotFoundException(chatId));

    }

    @Override
    public void deleteGroupChatById(UUID chatId, String userId) {
        var groupChat = this.getGroupById(chatId, userId);
        groupChat.setIsDeleted(true);
        groupChatRepository.save(groupChat);
    }

    @Override
    public GroupChat deleteUsersFromChat(UUID chatId, DeleteUsersRequest deleteUsersRequest, String userId) {
        var groupChat = this.getGroupById(chatId, userId);
        groupChat.removeUsers(deleteUsersRequest.users());
        return groupChatRepository.save(groupChat);
    }

    @Override
    public GroupChat changeNameGroup(UUID chatId, String name, String userId) {
        var groupChat = this.getGroupById(chatId, userId);
        groupChat.setName(name);
        return groupChatRepository.save(groupChat);
    }

}
