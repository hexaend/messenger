package ru.hexaend.user_service;

import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.StatusException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.grpc.server.exception.GrpcExceptionHandler;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;


@SpringBootApplication
@EnableDiscoveryClient
@EnableWebSecurity
@RestController
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }


    @Bean
    public GrpcExceptionHandler globalExceptionHandler() {
        return exception -> {
            if (exception instanceof IllegalArgumentException) {
                Metadata metadata = new Metadata();
                metadata.put(Metadata.Key.of("error-code", Metadata.ASCII_STRING_MARSHALLER), "INVALID_ARGUMENT");
                StatusException result = Status.INVALID_ARGUMENT.withDescription(exception.getMessage()).asException(metadata);
                return result;
            }
            return null;
        };
    }


}
