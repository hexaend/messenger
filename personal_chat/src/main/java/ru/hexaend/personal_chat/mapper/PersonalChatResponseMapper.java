package ru.hexaend.personal_chat.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import ru.hexaend.personal_chat.dto.kafka.PersonalChatCreated;
import ru.hexaend.personal_chat.dto.kafka.PersonalChatDeleted;
import ru.hexaend.personal_chat.dto.response.PersonalChatResponse;
import ru.hexaend.personal_chat.entity.PersonalChat;

@Mapper(componentModel="spring")
public interface PersonalChatResponseMapper {

    PersonalChatResponse toDto(PersonalChat personalChat);

    @Mapping(target = "chatId", source = "personalChat.id")
    PersonalChatCreated toKafkaCreatedDto(PersonalChat personalChat);

    @Mapping(target = "deletedAt", source = "personalChat.updatedAt")
    @Mapping(target = "chatId", source = "personalChat.id")
    @Mapping(target = "deletedBy", source = "personalChat.updatedBy")
    PersonalChatDeleted toKafkaDeletedDto(PersonalChat personalChat);

}
