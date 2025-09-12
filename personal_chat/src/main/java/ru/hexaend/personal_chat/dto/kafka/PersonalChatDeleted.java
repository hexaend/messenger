package ru.hexaend.personal_chat.dto.kafka;

import java.time.Instant;

public record PersonalChatDeleted(
        String chatId,
        String ownerId,
        String userId,
        String deletedAt,
        String deletedBy) {
}
