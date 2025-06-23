package com.eventplanner.controller;

import com.eventplanner.config.JwtUtil;
import com.eventplanner.dto.ApiResponse;
import com.eventplanner.dto.LoginRequest;
import com.eventplanner.dto.SignupRequest;
import com.eventplanner.model.User;
import com.eventplanner.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signup(@Valid @RequestBody SignupRequest signupRequest) {
        try {
            if (userService.existsByEmail(signupRequest.getEmail())) {
                return ResponseEntity.badRequest()
                        .body(new ApiResponse(false, "Email is already in use!"));
            }

            User user = userService.createUser(
                    signupRequest.getName(),
                    signupRequest.getEmail(),
                    signupRequest.getPassword()
            );

            String token = jwtUtil.generateToken(user.getEmail(), false);

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("user", Map.of(
                    "id", user.getId(),
                    "name", user.getName(),
                    "email", user.getEmail(),
                    "isAnonymous", user.isAnonymous()
            ));

            return ResponseEntity.ok(new ApiResponse(true, "User registered successfully!", response));

        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Registration failed: " + e.getMessage()));
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<ApiResponse> signin(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Optional<User> optionalUser = userService.findByEmail(loginRequest.getEmail());

            if (!optionalUser.isPresent()) {
                return ResponseEntity.badRequest()
                        .body(new ApiResponse(false, "Invalid email or password!"));
            }

            User user = optionalUser.get();

            if (!userService.validatePassword(loginRequest.getPassword(), user.getPassword())) {
                return ResponseEntity.badRequest()
                        .body(new ApiResponse(false, "Invalid email or password!"));
            }

            String token = jwtUtil.generateToken(user.getEmail(), false);

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("user", Map.of(
                    "id", user.getId(),
                    "name", user.getName(),
                    "email", user.getEmail(),
                    "isAnonymous", user.isAnonymous()
            ));

            return ResponseEntity.ok(new ApiResponse(true, "Login successful!", response));

        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Login failed: " + e.getMessage()));
        }
    }

    @PostMapping("/anonymous")
    public ResponseEntity<ApiResponse> signinAnonymous() {
        try {
            User anonymousUser = userService.createAnonymousUser();
            String token = jwtUtil.generateAnonymousToken();

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("user", Map.of(
                    "id", anonymousUser.getId(),
                    "name", anonymousUser.getName(),
                    "email", anonymousUser.getEmail(),
                    "isAnonymous", anonymousUser.isAnonymous()
            ));

            return ResponseEntity.ok(new ApiResponse(true, "Anonymous login successful!", response));

        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Anonymous login failed: " + e.getMessage()));
        }
    }
}