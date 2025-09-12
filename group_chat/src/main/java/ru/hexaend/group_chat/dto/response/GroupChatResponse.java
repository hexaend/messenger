package ru.hexaend.group_chat.dto.response;


import java.time.Instant;
import java.util.Set;
import java.util.UUID;

public record GroupChatResponse(Set<String> users, String name, String ownerId, Boolean isDeleted, UUID id,
                                Instant createdAt, Instant updatedAt) {
}
