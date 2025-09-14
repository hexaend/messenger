package ru.hexaend.group_chat.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;
import ru.hexaend.group_chat.dto.request.AddUsersRequest;
import ru.hexaend.group_chat.dto.request.CreateGroupChatRequest;
import ru.hexaend.group_chat.dto.request.DeleteUsersRequest;
import ru.hexaend.group_chat.entity.GroupChat;

import java.util.UUID;

@Service
public interface GroupChatService {

    GroupChat createGroupChat(CreateGroupChatRequest chatRequest, String owner_id);

    GroupChat  addUsersToGroupChat(UUID chatId, AddUsersRequest users, String userId);

    GroupChat getGroupById(UUID chatId, String userId);

    void deleteGroupChatById(UUID chatId, String userId);

    GroupChat deleteUsersFromChat(UUID chatId, @Valid DeleteUsersRequest deleteUsersRequest, String userId);

    GroupChat changeNameGroup(UUID chatId, String name, String userId);
}
