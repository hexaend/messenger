package ru.hexaend.group_chat.dto.response;

public record ErrorResponse(
        Boolean success,
        String code,
        String message
) {
}


