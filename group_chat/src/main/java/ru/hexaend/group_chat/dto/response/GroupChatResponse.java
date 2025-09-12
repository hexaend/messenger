package ru.hexaend.group_chat.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

public record GroupChatResponse(
        UUID id,
        String name,
        @JsonProperty("owner_id")
        String ownerId,
        Set<String> users,
        @JsonProperty("is_deleted")
        Boolean isDeleted,
        @JsonProperty("created_at")
        Instant createdAt,
        @JsonProperty("updated_at")
        Instant updatedAt) {
}
