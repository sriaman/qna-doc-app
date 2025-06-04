package com.jktech.config;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class JwtConfig {
    // This key must be shared between signing (AuthServiceImpl) and verifying (JwtAuthFilter)
    public static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
}

