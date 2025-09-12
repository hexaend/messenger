package ru.hexaend.group_chat.exceptions.custom;

public class MissingChatIdentifierException extends RuntimeException {
    public MissingChatIdentifierException(String message) {
        super(message);
    }
}
