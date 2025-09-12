//package ru.hexaend.user_service.config;
//
//import com.ecwid.consul.v1.ConsulClient;
//import com.ecwid.consul.v1.agent.model.NewService;
//import jakarta.annotation.PostConstruct;
//import jakarta.annotation.PreDestroy;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.consul.ConsulProperties;
//import org.springframework.context.annotation.Configuration;
//
//
//@Slf4j
//@Configuration
//public class ConsulConfig {
//
//    @Value("${spring.application.name}")
//    private String appName;
//
//    @Value("${spring.grpc.server.port}")
//    private Integer grpcPort;
//
//    @Autowired
//    private ConsulClient consul;
//
//    private String serviceId;
//    @Autowired
//    private ConsulProperties consulProperties;
//
//    @PostConstruct
//    public void registerGrpcService() {
//        serviceId = appName + "-grpc-" + grpcPort;
//        NewService grpcService = new NewService();
//        grpcService.setName(appName + "-grpc");
//        grpcService.setPort(grpcPort);
//        grpcService.setAddress("localhost");
//        grpcService.setId(serviceId);
//
//        NewService.Check grpcCheck = new NewService.Check();
//        grpcCheck.setGrpc("localhost:" + grpcPort);
//        grpcCheck.setInterval("10s");
//        grpcCheck.setDeregisterCriticalServiceAfter("1m");
//        grpcService.setCheck(grpcCheck);
//
//        consul.agentServiceRegister(grpcService);
//        log.info("Registered gRPC service with Consul: {} on port {}", grpcService.getName(), grpcService.getPort());
//    }
//
//    @PreDestroy
//    public void deregisterGrpcService() {
//        if (serviceId != null) {
//            log.info("TEST: {}", consulProperties);
//            consul.agentServiceDeregister(serviceId);
//            log.info("Deregistered gRPC service from Consul: {}", serviceId);
//        }
//    }
//}