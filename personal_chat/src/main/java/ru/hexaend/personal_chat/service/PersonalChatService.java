package ru.hexaend.personal_chat.service;

import ru.hexaend.personal_chat.entity.PersonalChat;

import java.util.UUID;

public interface PersonalChatService {

    PersonalChat getPersonalChatByChatId(UUID chatId);
    PersonalChat getPersonalChatByUsername(String username, String owner);
    PersonalChat getPersonalChatByUserId(String userId, String owner);

    PersonalChat createPersonalChatByUsername(String username, String ownerId);
    PersonalChat createPersonalChatByUserId(String userId, String ownerId);

    void deletePersonalChatByChatId(UUID chatId);
    void deletePersonalChatByUsername(String username, String owner);
    void deletePersonalChatByUserId(String userId, String owner);

}
