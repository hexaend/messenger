package ru.hexaend.personal_chat.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.hexaend.personal_chat.dto.response.ErrorResponse;
import ru.hexaend.personal_chat.exceptions.custom.PersonalChatNotFoundException;
import ru.hexaend.personal_chat.exceptions.custom.UserNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        var error = new ErrorResponse(false,"INTERNAL_SERVER_ERROR", e.getMessage());
        return ResponseEntity.status(500).body(error);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException e) {
        var error = new ErrorResponse(false,"USER_NOT_FOUND", e.getMessage());
        return ResponseEntity.status(404).body(error);
    }

    @ExceptionHandler(PersonalChatNotFoundException.class)
    public ResponseEntity<?> handlePersonalChatNotFoundException(PersonalChatNotFoundException e) {
        var error = new ErrorResponse(false,"PERSONAL_CHAT_NOT_FOUND", e.getMessage());
        return ResponseEntity.status(404).body(error);
    }
}
