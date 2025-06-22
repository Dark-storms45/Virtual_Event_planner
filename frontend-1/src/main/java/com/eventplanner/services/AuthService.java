package com.eventplanner.services;

import com.eventplanner.config.ApiConfig;
import com.eventplanner.models.User;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AuthService {
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
    private final ApiClient apiClient;
    private final ObjectMapper objectMapper;

    public AuthService() {
        this.apiClient = ApiClient.getInstance();
        this.objectMapper = new ObjectMapper();
    }

    public User login(String email, String password) throws IOException {
        logger.info("Attempting login for email: {}", email);
        
        Map<String, String> loginRequest = new HashMap<>();
        loginRequest.put("email", email);
        loginRequest.put("password", password);

        try {
            // Make login request
            String response = apiClient.post(ApiConfig.AUTH_LOGIN, loginRequest, String.class);
            JsonNode jsonNode = objectMapper.readTree(response);
            
            // Extract token and user info
            String token = jsonNode.get("token").asText();
            JsonNode userNode = jsonNode.get("user");
            
            // Set token for future requests
            apiClient.setAuthToken(token);
            
            // Convert user data to User object
            User user = objectMapper.treeToValue(userNode, User.class);
            
            logger.info("Login successful for user: {}", email);
            return user;
            
        } catch (IOException e) {
            logger.error("Login failed for email: {}", email, e);
            throw new IOException("Login failed: " + e.getMessage());
        }
    }

    public User register(String name, String email, String password) throws IOException {
        logger.info("Attempting registration for email: {}", email);
        
        Map<String, String> registerRequest = new HashMap<>();
        registerRequest.put("name", name);
        registerRequest.put("email", email);
        registerRequest.put("password", password);

        try {
            // Make registration request
            String response = apiClient.post(ApiConfig.AUTH_REGISTER, registerRequest, String.class);
            JsonNode jsonNode = objectMapper.readTree(response);
            
            // Extract token and user info
            String token = jsonNode.get("token").asText();
            JsonNode userNode = jsonNode.get("user");
            
            // Set token for future requests
            apiClient.setAuthToken(token);
            
            // Convert user data to User object
            User user = objectMapper.treeToValue(userNode, User.class);
            
            logger.info("Registration successful for user: {}", email);
            return user;
            
        } catch (IOException e) {
            logger.error("Registration failed for email: {}", email, e);
            throw new IOException("Registration failed: " + e.getMessage());
        }
    }

    public void logout() {
        apiClient.setAuthToken(null);
        logger.info("User logged out");
    }
}