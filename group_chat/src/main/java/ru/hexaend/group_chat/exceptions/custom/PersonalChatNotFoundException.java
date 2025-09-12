package ru.hexaend.group_chat.exceptions.custom;

public class PersonalChatNotFoundException extends RuntimeException {
    public PersonalChatNotFoundException(String message) {
        super(message);
    }
    public PersonalChatNotFoundException() {
        super("Personal chat not found");
    }
}
