package ru.hexaend.group_chat.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hexaend.group_chat.dto.request.AddUsersRequest;
import ru.hexaend.group_chat.dto.request.CreateGroupChatRequest;
import ru.hexaend.group_chat.dto.request.DeleteUsersRequest;
import ru.hexaend.group_chat.dto.response.GroupChatResponse;
import ru.hexaend.group_chat.entity.GroupChat;
import ru.hexaend.group_chat.grpc.GrpcClientService;
import ru.hexaend.group_chat.mapper.GroupChatMapper;
import ru.hexaend.group_chat.service.GroupChatService;

import java.security.Principal;
import java.util.HashSet;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class GroupChatController {

    private final GroupChatService groupChatService;
    private final GroupChatMapper groupChatMapper;
    private final GrpcClientService grpcClientService;

    @PostMapping
    public ResponseEntity<?> createGroupChat(@Valid @RequestBody CreateGroupChatRequest users,
                                             @RequestParam(value = "type", required = false) String type,
                                             Principal principal) {
        if (type.equals("username")) {
            var newUsers1 = grpcClientService.getIdsByUsernames(users.users());
            users = new CreateGroupChatRequest(new HashSet<>(newUsers1), users.name());
        }

        var newUsers = users.users();
        newUsers.add(principal.getName());
        users = new CreateGroupChatRequest(new HashSet<>(newUsers), users.name());


        GroupChat groupChat = groupChatService.createGroupChat(users, principal.getName());
        GroupChatResponse groupChatResponse = groupChatMapper.toDto(groupChat);
        return ResponseEntity.ok(groupChatResponse);
    }

    @PutMapping("/{chatId}")
    public ResponseEntity<?> putUserToGroupChat(
            @Valid @org.hibernate.validator.constraints.UUID(message="Chat id must be UUID") @PathVariable UUID chatId,
            @Valid @RequestBody AddUsersRequest users,
            @RequestParam(value = "type", required = false) String type,
            Principal principal) {

        if (type.equals("username")) {
            var userList = grpcClientService.getIdsByUsernames(users.users());
            users = new AddUsersRequest(new HashSet<>(userList));
        }

        GroupChat groupChat = groupChatService.addUsersToGroupChat(chatId, users, principal.getName());
        GroupChatResponse groupChatResponse = groupChatMapper.toDto(groupChat);
        return ResponseEntity.ok(groupChatResponse);
    }

    @GetMapping("/{chatId}")
    public ResponseEntity<?> getGroupChatById(
            @Valid @org.hibernate.validator.constraints.UUID(message="Chat id must be UUID") @PathVariable UUID chatId,
            Principal principal) {
        GroupChat groupChat = groupChatService.getGroupById(chatId, principal.getName());
        GroupChatResponse groupChatResponse = groupChatMapper.toDto(groupChat);
        return ResponseEntity.ok(groupChatResponse);
    }

    @DeleteMapping("/{chatId}")
    public ResponseEntity<?> deleteGroupChatById(
            @Valid @org.hibernate.validator.constraints.UUID(message="Chat id must be UUID") @PathVariable UUID chatId,
            Principal principal) {

        groupChatService.deleteGroupChatById(chatId, principal.getName());
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{chatId}")
    public ResponseEntity<?> deleteGroupChatById(
            @Valid @org.hibernate.validator.constraints.UUID(message="Chat id must be UUID") @PathVariable UUID chatId,
            @Valid DeleteUsersRequest deleteUsersRequest,
            @RequestParam(value = "type", required = false) String type,
            Principal principal) {
        if (type.equals("username")) {
            var userList = grpcClientService.getIdsByUsernames(deleteUsersRequest.users());
            deleteUsersRequest = new DeleteUsersRequest(new HashSet<>(userList));
        }

        GroupChat groupChat = groupChatService.deleteUsersFromChat(chatId, deleteUsersRequest, principal.getName());
        GroupChatResponse groupChatResponse = groupChatMapper.toDto(groupChat);
        return ResponseEntity.ok(groupChatResponse);
    }

    @PutMapping("/{chatId}")
    public ResponseEntity<?> changeGroupChatName(
            @Valid @org.hibernate.validator.constraints.UUID(message="Chat id must be UUID") @PathVariable UUID chatId,
            @RequestParam @NotBlank(message = "Name cannot be blank") String name,
            Principal principal) {

        GroupChat groupChat = groupChatService.changeNameGroup(chatId, name, principal.getName());
        GroupChatResponse groupChatResponse = groupChatMapper.toDto(groupChat);
        return ResponseEntity.ok(groupChatResponse);
    }

}
