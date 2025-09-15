package ru.hexaend.group_chat.kafka.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.hexaend.group_chat.entity.GroupChat;
import ru.hexaend.group_chat.kafka.GroupChatKafkaService;
import ru.hexaend.group_chat.mapper.GroupChatMapper;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class GroupChatKafkaServiceImpl implements GroupChatKafkaService {
    private final KafkaTemplate<Object, Object> kafkaTemplate;
    private final GroupChatMapper groupChatMapper;

    @Override
    public void createGroupChat(GroupChat groupChat) {
        var gChat = groupChatMapper.toKafkaDtoCreated(groupChat);
        // TODO: move topic to properties
        kafkaTemplate.send("group-chat-events", gChat);
    }

    @Override
    public void renamedGroupChat(GroupChat groupChat, String oldName) {
        var gChat = groupChatMapper.toKafkaDtoRenamed(groupChat, oldName);
        kafkaTemplate.send("group-chat-events", gChat);
    }

    @Override
    public void addUsersToGroupChat(GroupChat groupChat, Set<String> newUsers) {
        var gChat = groupChatMapper.toKafkaDtoUsersAdded(groupChat, newUsers);
        kafkaTemplate.send("group-chat-events", gChat);
    }

    @Override
    public void deleteUsersFromGroupChat(GroupChat groupChat, Set<String> deletedUsers) {
        var gChat = groupChatMapper.toKafkaDtoUsersDeleted(groupChat, deletedUsers);
        kafkaTemplate.send("group-chat-events", gChat);
    }

    @Override
    public void deleteGroupChat(GroupChat groupChat) {
        var gChat = groupChatMapper.toKafkaDtoDeleted(groupChat);
        kafkaTemplate.send("group-chat-events", gChat);
    }
}
