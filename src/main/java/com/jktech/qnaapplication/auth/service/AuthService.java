package com.jktech.qnaapplication.auth.service;

import com.jktech.qnaapplication.auth.dto.LoginRequest;
import com.jktech.qnaapplication.auth.model.AuthResponse;
import com.jktech.qnaapplication.auth.model.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    String register(User user);
    ResponseEntity<AuthResponse> login(LoginRequest request);
    String logout(HttpServletRequest request);
}
