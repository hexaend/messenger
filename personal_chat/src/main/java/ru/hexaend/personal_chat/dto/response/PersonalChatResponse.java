package ru.hexaend.personal_chat.dto.response;


import java.util.UUID;

public record PersonalChatResponse(UUID id, String ownerId, String userId, Boolean isDeleted, String createdAt, String updatedAt) {
}
