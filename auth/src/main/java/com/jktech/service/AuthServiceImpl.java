package com.jktech.service;

import com.jktech.model.TokenBlacklist;
import com.jktech.models.AuthResponse;
import com.jktech.repository.TokenBlacklistRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import com.jktech.dto.LoginRequest;
import com.jktech.models.User;
import com.jktech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import static com.jktech.config.JwtConfig.SECRET_KEY;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenBlacklistRepository blacklistRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public String register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully!";
    }

    @Override
    public ResponseEntity<AuthResponse> login(LoginRequest request) {
        Optional<User> user = userRepository.findByUsername(request.getUsername());
        if (user.isPresent() && encoder.matches(request.getPassword(), user.get().getPassword())) {
            String token = Jwts.builder()
                    .setSubject(user.get().getUsername())
                    .claim("role", user.get().getRole())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day
                    .signWith(SECRET_KEY)
                    .compact();

            return ResponseEntity.ok(new AuthResponse(token));  // 👈 Return the JWT token
        }
        return ResponseEntity.ok(new AuthResponse("Invalid Credentials"));
    }


    @Override
    public String logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            LocalDateTime expiry = extractExpiry(token);
            blacklistRepository.save(new TokenBlacklist(token, expiry));
            return "Logout successful";
        }
        return "Invalid token";
    }

    private LocalDateTime extractExpiry(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey("secret".getBytes()) // replace with your secret key
                .parseClaimsJws(token)
                .getBody();
        Date expiration = claims.getExpiration();
        return expiration.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime();
    }
}
