package ru.hexaend.group_chat.mapper;

import org.mapstruct.*;
import ru.hexaend.group_chat.dto.response.GroupChatResponse;
import ru.hexaend.group_chat.entity.GroupChat;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface GroupChatMapper {
    GroupChat toEntity(GroupChatResponse groupChatResponse);

    GroupChatResponse toDto(GroupChat groupChat);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    GroupChat partialUpdate(GroupChatResponse groupChatResponse, @MappingTarget GroupChat groupChat);
}