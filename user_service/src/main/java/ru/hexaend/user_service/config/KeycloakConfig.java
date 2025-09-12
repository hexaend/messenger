package ru.hexaend.user_service.config;

import lombok.Getter;
import lombok.Setter;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "keycloak")
@Configuration
@Getter
@Setter
public class KeycloakConfig {

    private String serverUrl;
    private String realm;
    private String username;
    private String password;
    private String clientId;

    @Bean
    public Keycloak keycloak() {
        return Keycloak.getInstance(
                serverUrl,
                realm,
                username,
                password,
                clientId);
    }

}
