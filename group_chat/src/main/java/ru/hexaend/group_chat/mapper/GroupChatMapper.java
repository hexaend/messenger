package ru.hexaend.group_chat.mapper;

import org.mapstruct.*;
import ru.hexaend.group_chat.dto.kafka.*;
import ru.hexaend.group_chat.dto.request.CreateGroupChatRequest;
import ru.hexaend.group_chat.dto.response.GroupChatResponse;
import ru.hexaend.group_chat.entity.GroupChat;

import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface GroupChatMapper {
    GroupChat toEntity(GroupChatResponse groupChatResponse);

    GroupChatResponse toDto(GroupChat groupChat);

    GroupChat fromDto(CreateGroupChatRequest groupChatResponse);

    GroupChatCreated toKafkaDtoCreated(GroupChat groupChat);

    GroupChatDeleted toKafkaDtoDeleted(GroupChat groupChat);

    @Mapping(target = "newName", source = "groupChat.name")
    GroupChatRenamed toKafkaDtoRenamed(GroupChat groupChat, String oldName);

    @Mapping(target = "currentUsers", source = "groupChat.users")
    GroupChatUsersAdded toKafkaDtoUsersAdded(GroupChat groupChat, Set<String> newUsers);

    GroupChatUsersRemoved toKafkaDtoUsersDeleted(GroupChat groupChat, Set<String> deletedUsers);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    GroupChat partialUpdate(GroupChatResponse groupChatResponse, @MappingTarget GroupChat groupChat);
}