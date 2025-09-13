package ru.hexaend.group_chat.dto.kafka;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

public record GroupChatCreated(
        UUID id,
        String name,
        String owner,
        Set<String> users,
        Instant createdAt
) {
}
