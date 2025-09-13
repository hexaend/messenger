package ru.hexaend.group_chat.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hexaend.group_chat.dto.request.CreateGroupChatRequest;
import ru.hexaend.group_chat.dto.response.GroupChatResponse;
import ru.hexaend.group_chat.entity.GroupChat;
import ru.hexaend.group_chat.mapper.GroupChatMapper;
import ru.hexaend.group_chat.service.GroupChatService;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class GroupChatController {

    private final GroupChatService groupChatService;
    private final GroupChatMapper groupChatMapper;

    @PostMapping
    public ResponseEntity<?> createGroupChat(
            @Valid @RequestBody CreateGroupChatRequest users,
            Principal principal) {

        GroupChat groupChat = groupChatService.createGroupChat(users, principal.getName());
        GroupChatResponse groupChatResponse = groupChatMapper.toDto(groupChat);
        return ResponseEntity.ok(groupChatResponse);
    }



}
