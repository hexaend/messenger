package ru.hexaend.group_chat.exceptions.custom;

import java.util.UUID;

public class GroupChatNotFoundException extends RuntimeException {
    public GroupChatNotFoundException(String message) {
        super(message);
    }
    public GroupChatNotFoundException(UUID message) {
        super("Group chat with id " + message + " not found");
    }
    public GroupChatNotFoundException() {
        super("Group chat not found");
    }
}
