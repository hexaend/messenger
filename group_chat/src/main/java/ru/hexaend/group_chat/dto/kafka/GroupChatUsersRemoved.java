package ru.hexaend.group_chat.dto.kafka;

import java.time.Instant;

public record GroupChatUsersRemoved(
        String id,
        String name,
        String updatedBy,
        Instant updatedAt,
        String[] removedUsers,
        String[] currentUsers
) {
}
