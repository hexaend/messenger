package ru.hexaend.personal_chat.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record PersonalChatResponse(
        UUID id,
        @JsonProperty("owner_id")
        String ownerId,
        @JsonProperty("user_id")
        String userId,
        @JsonProperty("is_deleted")
        Boolean isDeleted,
        @JsonProperty("created_at")
        String createdAt,
        @JsonProperty("updated_at")
        String updatedAt) {
}
