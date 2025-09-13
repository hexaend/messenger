package ru.hexaend.group_chat.grpc.impl;

import io.grpc.Metadata;
import io.grpc.stub.MetadataUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.grpc.client.GrpcChannelFactory;
import org.springframework.grpc.server.service.GrpcService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import ru.hexaend.group_chat.exceptions.custom.UserNotFoundException;
import ru.hexaend.group_chat.grpc.GrpcClientService;
import ru.hexaend.personal_chat.proto.ListOfStrings;
import ru.hexaend.personal_chat.proto.ResultResponse;
import ru.hexaend.personal_chat.proto.SimpleGrpc;

import java.util.Objects;

@Slf4j
@GrpcService
@RequiredArgsConstructor
public class GrpcClientServiceImpl implements GrpcClientService {

//    private final SimpleGrpc.SimpleBlockingStub simpleBlockingStub;
    private final GrpcChannelFactory grpcChannelFactory;
    private final DiscoveryClient discoveryClient;
    private final LoadBalancerClient loadBalancerClient;

    public String getIdByUsername(String username) {
        ListOfStrings request = ListOfStrings.newBuilder().addItems(username).build();

        JwtAuthenticationToken principal = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        Metadata metadata = new Metadata();
        metadata.put(Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER), "Bearer " + principal.getToken().getTokenValue());

        var serviceInstances = discoveryClient.getInstances("user-service");
        ServiceInstance serviceInstanceBest = null;
        for (ServiceInstance serviceInstance : serviceInstances) {
            serviceInstanceBest = loadBalancerClient.choose(serviceInstance.getServiceId());
              if (serviceInstanceBest != null) {
                  break;
              }
        }

        assert serviceInstanceBest != null;
        var stub = SimpleGrpc
                .newBlockingStub(grpcChannelFactory.createChannel(serviceInstanceBest.getHost() + ":" + serviceInstanceBest.getPort()))
                .withInterceptors(MetadataUtils.newAttachHeadersInterceptor(metadata));
        ResultResponse a = stub.usernamesToIDs(request);

        String result = a.getResultsMap().get(username);
        if (Objects.equals(result, "Not found")) {
            throw new UserNotFoundException("User: " + username + "not found");
        }

        return result;
    }

}
