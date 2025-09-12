//package ru.hexaend.user_service.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.grpc.server.GlobalServerInterceptor;
//import org.springframework.grpc.server.security.AuthenticationProcessInterceptor;
//import org.springframework.grpc.server.security.GrpcSecurity;
//
//@Configuration
//public class GrpcSecurityConfig {
//
//    @Bean
////    @GlobalServerInterceptor
//    AuthenticationProcessInterceptor test(GrpcSecurity grpc) throws Exception {
//        return grpc
//                .authorizeRequests(requests -> requests
//                        .methods("UserServiceProto/UsernamesToIDs").authenticated()
//                        .methods("grpc.*/*").permitAll()
//                        .allRequests().denyAll())
//                .build();
//    }
//}
//
//
///*
//@Configuration
//class MySecurityCfg extends  {
//    @Override
//    public void configure(GrpcSecurity builder) throws Exception {
//        MethodsDescriptor<?,?> adminMethods = MyServiceGrpc.getSomeMethod();
//        builder
//                .authorizeRequests()
//                .methods(adminMethods).hasAnyRole("admin")
//                .anyMethodExcluding(adminMethods).hasAnyRole("user")
//                .withSecuredAnnotation();(1)
//    }
//}
//*/
