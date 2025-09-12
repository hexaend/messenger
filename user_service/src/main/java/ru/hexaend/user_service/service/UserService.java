package ru.hexaend.user_service.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface UserService {

    Map<String, String> getUserIDsByUsernames(List<String> usernames);
    Map<String, String> getUserIDByUsername(String username);

}
