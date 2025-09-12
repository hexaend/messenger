package ru.hexaend.personal_chat.grpc;

import lombok.RequiredArgsConstructor;
import org.springframework.grpc.server.service.GrpcService;
import ru.hexaend.personal_chat.proto.ListOfStrings;
import ru.hexaend.personal_chat.proto.ResultResponse;
import ru.hexaend.personal_chat.proto.SimpleGrpc;

@GrpcService
public interface GrpcClientService  {


    String getIdByUsername(String username);

}
