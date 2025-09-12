package ru.hexaend.user_service.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.hexaend.user_service.service.UserService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final Keycloak keycloak;

    @Value("${keycloak.main_realm}")
    private String realm;

    @Override
    public Map<String, String> getUserIDsByUsernames(List<String> usernames) {
        return usernames.stream().map(this::getUserIDByUsername)
                .flatMap(m -> m.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public Map<String, String> getUserIDByUsername(String username) {
        try {
            return Map.of(username, keycloak.realm(realm).users().search(username).getFirst().getId());
        } catch (RuntimeException e) {
            return Map.of(username, "Not found");
        }
    }
}
