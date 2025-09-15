package ru.hexaend.group_chat.dto.kafka;

import java.time.Instant;

public record GroupChatDeleted(
        String id,
        String name,
        Instant updatedAt,
        String updatedBy
) {
}
