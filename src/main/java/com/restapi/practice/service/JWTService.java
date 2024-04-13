package com.restapi.practice.service;

import java.sql.Date;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.restapi.practice.model.dto.requests.AuthRequest;

@Service
public class JWTService {
    @Value("${SECRET}")
    private String SECRET;
    
    @Value("${EXPIRATION_TIME}")
    private long EXPIRATION_TIME;
    
    @Value("${TOKEN_PREFIX}")
    private String TOKEN_PREFIX;
    
    @Value("${HEADER_PREFIX}")
    private String HEADER_PREFIX;

    public String generateToken(AuthRequest authRequest) {
        return JWT
                .create()
                .withClaim("username", Base64.getEncoder().encodeToString(authRequest.getUsername().getBytes()))
                .withClaim("password", Base64.getEncoder().encodeToString(authRequest.getPassword().getBytes()))
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(Base64.getEncoder().encodeToString(SECRET.getBytes())));
    }
}
