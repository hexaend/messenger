package ru.hexaend.personal_chat.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hexaend.personal_chat.dto.response.DeleteSuccessResponse;
import ru.hexaend.personal_chat.dto.response.PersonalChatResponse;
import ru.hexaend.personal_chat.entity.PersonalChat;
import ru.hexaend.personal_chat.exceptions.custom.MissingChatIdentifierException;
import ru.hexaend.personal_chat.mapper.PersonalChatResponseMapper;
import ru.hexaend.personal_chat.service.PersonalChatService;

import java.security.Principal;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping
public class PersonalChatController {

    private final PersonalChatService personalChatService;
    private final PersonalChatResponseMapper personalChatResponseMapper;

    public PersonalChatController(PersonalChatService personalChatService, PersonalChatResponseMapper personalChatResponseMapper) {
        this.personalChatService = personalChatService;
        this.personalChatResponseMapper = personalChatResponseMapper;
    }

    @GetMapping
    public ResponseEntity<?> getPersonalChat(
            @RequestParam(required = false) String username,
            @RequestParam(required = false, name="user_id") String userId,
            @RequestParam(required = false, name="chat_id") UUID chatId,
            Principal principal) {

        String owner = principal.getName();
        PersonalChat personalChat;

        if (username != null) {
            personalChat = personalChatService.getPersonalChatByUsername(username, owner);
        } else if (userId != null) {
            personalChat = personalChatService.getPersonalChatByUserId(userId, owner);
        } else if (chatId != null) {
            personalChat = personalChatService.getPersonalChatByChatId(chatId);
        } else {
            throw new MissingChatIdentifierException("At least one identifier (username, user_id, chat_id) must be provided");
        }
        PersonalChatResponse response = personalChatResponseMapper.toDto(personalChat);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> createPersonalChat(
            @RequestParam(required = false) String username,
            @RequestParam(required = false, name = "user_id") String userId,
            Principal principal) {
        PersonalChat personalChat;
        if (username != null) {
            personalChat = personalChatService.createPersonalChatByUsername(username, principal.getName());
        } else if (userId != null) {
            personalChat = personalChatService.createPersonalChatByUserId(userId, principal.getName());
        } else {
            throw new MissingChatIdentifierException("Either username or user_id must be provided");
        }

        return ResponseEntity.ok(personalChatResponseMapper.toDto(personalChat));
    }

    @DeleteMapping
    public ResponseEntity<?> deletePersonalChat(
            @RequestParam(required = false) String username,
            @RequestParam(required = false, name = "user_id") String userId,
            @RequestParam(required = false, name = "chat_id") UUID chatId,
            Principal principal
    ){
        if (username != null) {
            personalChatService.deletePersonalChatByUsername(username, principal.getName());
        } else if (userId != null) {
            personalChatService.deletePersonalChatByUserId(userId, principal.getName());
        } else if (chatId != null) {
            personalChatService.deletePersonalChatByChatId(chatId);
        } else {
            throw new MissingChatIdentifierException("At least one identifier (username, user_id, chat_id) must be provided");
        }
        return ResponseEntity.ok(new DeleteSuccessResponse("Personal chat deleted successfully"));
    }



}
