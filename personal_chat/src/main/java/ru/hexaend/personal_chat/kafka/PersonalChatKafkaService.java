package ru.hexaend.personal_chat.kafka;


import ru.hexaend.personal_chat.entity.PersonalChat;

public interface PersonalChatKafkaService {
    void personalChatCreated(PersonalChat personalChat);
    void personalChatDeleted(PersonalChat personalChat);
}
