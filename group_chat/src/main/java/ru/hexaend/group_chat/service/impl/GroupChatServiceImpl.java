package ru.hexaend.group_chat.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hexaend.group_chat.dto.request.CreateGroupChatRequest;
import ru.hexaend.group_chat.entity.GroupChat;
import ru.hexaend.group_chat.kafka.GroupChatKafkaService;
import ru.hexaend.group_chat.mapper.GroupChatMapper;
import ru.hexaend.group_chat.repository.GroupChatRepository;
import ru.hexaend.group_chat.service.GroupChatService;

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
}
