package ru.hexaend.group_chat.dto.request;

import jakarta.validation.constraints.NotEmpty;

import java.util.Set;

public record CreateGroupChatRequest(
        @NotEmpty(message = "Group chat must have at least one user")
        Set<@NotEmpty(message = "Username may not be blank") String> users,
        @NotEmpty(message = "Group chat name may not be blank")
        String name
)
{}