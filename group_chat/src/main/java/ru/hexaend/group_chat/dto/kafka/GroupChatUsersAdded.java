package ru.hexaend.group_chat.dto.kafka;

import java.time.Instant;

public record GroupChatUsersAdded(
        String id,
        String name,
        String updatedBy,
        Instant updatedAt,
        String[] newUsers,
        String[] currentUsers
) {
}
