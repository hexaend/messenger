package ru.hexaend.group_chat.kafka.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.hexaend.group_chat.entity.GroupChat;
import ru.hexaend.group_chat.kafka.GroupChatKafkaService;
import ru.hexaend.group_chat.mapper.GroupChatMapper;

@Service
@RequiredArgsConstructor
public class GroupChatKafkaServiceImpl implements GroupChatKafkaService {
    private final KafkaTemplate<Object, Object> kafkaTemplate;
    private final GroupChatMapper groupChatMapper;

    @Override
    public void createGroupChat(GroupChat groupChat) {
        var gChat = groupChatMapper.toKafkaDto(groupChat);
        // TODO: move topic to properties
        kafkaTemplate.send("group-chat-events", gChat);
    }
}
