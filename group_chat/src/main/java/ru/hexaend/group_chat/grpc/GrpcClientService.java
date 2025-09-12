package ru.hexaend.group_chat.grpc;

import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public interface GrpcClientService  {


    String getIdByUsername(String username);

}
