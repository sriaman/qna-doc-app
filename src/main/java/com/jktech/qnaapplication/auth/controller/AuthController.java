package com.jktech.qnaapplication.auth.controller;


import com.jktech.qnaapplication.auth.dto.LoginRequest;
import com.jktech.qnaapplication.auth.model.AuthResponse;
import com.jktech.qnaapplication.auth.model.User;
import com.jktech.qnaapplication.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        return ResponseEntity.ok(authService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("You are logged out.");
    }

    @GetMapping("/me")
    public ResponseEntity<String> currentUser(Authentication authentication) {
        return ResponseEntity.ok("Logged in as: " + authentication.getName());
    }
}
