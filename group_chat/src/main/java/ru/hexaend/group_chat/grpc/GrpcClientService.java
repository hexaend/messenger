package ru.hexaend.group_chat.grpc;

import jakarta.validation.Valid;
import org.springframework.grpc.server.service.GrpcService;
import ru.hexaend.group_chat.dto.request.CreateGroupChatRequest;

import java.util.List;

@GrpcService
public interface GrpcClientService  {


    String getIdByUsername(String username);

    List<String> getIdsByUsernames(Iterable<String> usernames);
}
