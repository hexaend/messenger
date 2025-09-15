package ru.hexaend.group_chat.kafka;

import org.springframework.stereotype.Service;
import ru.hexaend.group_chat.entity.GroupChat;

import java.util.Set;

@Service
public interface GroupChatKafkaService {
    void createGroupChat(GroupChat groupChat);
    void renamedGroupChat(GroupChat groupChat, String oldName);
    void addUsersToGroupChat(GroupChat groupChat, Set<String> newUsers);
    void deleteUsersFromGroupChat(GroupChat groupChat, Set<String> deletedUsers);
    void deleteGroupChat(GroupChat groupChat);
}
