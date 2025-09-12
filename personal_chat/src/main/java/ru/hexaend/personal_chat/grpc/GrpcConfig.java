package ru.hexaend.personal_chat.grpc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.grpc.client.GrpcChannelFactory;
import ru.hexaend.personal_chat.proto.SimpleGrpc;

@Configuration
public class GrpcConfig {

//    @Bean
//    SimpleGrpc.SimpleBlockingStub simpleBlockingStub(GrpcChannelFactory grpcChannelFactory) {
//        return SimpleGrpc.newBlockingStub(grpcChannelFactory.createChannel("0.0.0.0:8081"));
//    }

}
