package ru.hexaend.group_chat.service;

import org.springframework.stereotype.Service;
import ru.hexaend.group_chat.dto.request.CreateGroupChatRequest;
import ru.hexaend.group_chat.entity.GroupChat;

@Service
public interface GroupChatService {

    GroupChat createGroupChat(CreateGroupChatRequest chatRequest, String owner_id);

}
