package ru.hexaend.personal_chat.dto.kafka;

public record PersonalChatCreated(
        String chatId,
        String ownerId,
        String userId,
        String createdAt
) {
}
