package ru.hexaend.group_chat.kafka;

import org.springframework.stereotype.Service;
import ru.hexaend.group_chat.entity.GroupChat;

import java.util.Set;

@Service
public interface GroupChatKafkaService {

    void createGroupChat(GroupChat groupChat);

}
