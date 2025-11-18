package com.controller;

import com.model.User;
import com.security.JwtTokenUtil;
import com.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthController(UserService userService, JwtTokenUtil jwtTokenUtil) {
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userService.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body(Map.of("message", "Email already in use"));
        }
        User saved = userService.registerUser(user);
        return ResponseEntity.ok(Map.of("message", "Registration successful", "userId", saved.getId()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");
        if (!userService.authenticate(email, password)) {
            return ResponseEntity.status(401).body(Map.of("message", "Invalid credentials"));
        }
        Optional<User> u = userService.findByEmail(email);
        if (u.isEmpty()) return ResponseEntity.status(404).body(Map.of("message", "User not found"));
        User user = u.get();
        String token = jwtTokenUtil.generateToken(user.getEmail());
        return ResponseEntity.ok(Map.of("token", token, "userId", user.getId(), "email", user.getEmail()));
    }
}
