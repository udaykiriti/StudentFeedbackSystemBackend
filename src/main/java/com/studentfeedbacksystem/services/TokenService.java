package com.studentfeedbacksystem.services;

import com.studentfeedbacksystem.models.User;
import com.studentfeedbacksystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class TokenService {

    private final Map<String, User> tokenStore = new HashMap<>(); // Store tokens with users (use DB in production)

    @SuppressWarnings("unused")
	@Autowired
    private UserRepository userRepository;

    // Generates a token and stores it with the associated user
    public String generateTokenForUser(User user) {
        String token = UUID.randomUUID().toString();
        tokenStore.put(token, user);
        return token;
    }

    // Validates token and retrieves the associated user
    public User validateTokenAndGetUser(String token) {
        return tokenStore.get(token);
    }

    // Removes token after use (optional)
    public void invalidateToken(String token) {
        tokenStore.remove(token);
    }
}
