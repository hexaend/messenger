package ru.hexaend.group_chat.dto.kafka;

import java.time.Instant;

public record GroupChatRenamed(
        String id,
        String oldName,
        String newName,
        Instant updatedAt,
        String updatedBy
) {
}
