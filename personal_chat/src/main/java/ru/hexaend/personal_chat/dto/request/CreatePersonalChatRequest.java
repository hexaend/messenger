package ru.hexaend.personal_chat.dto.request;


import jakarta.validation.constraints.NotBlank;

public record CreatePersonalChatRequest(@NotBlank(message = "Username may not be blank") String user) {
}
