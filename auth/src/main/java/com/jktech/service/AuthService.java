package com.jktech.service;

import com.jktech.dto.LoginRequest;
import com.jktech.models.AuthResponse;
import com.jktech.models.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    String register(User user);
    ResponseEntity<AuthResponse> login(LoginRequest request);
    String logout(HttpServletRequest request);
}
