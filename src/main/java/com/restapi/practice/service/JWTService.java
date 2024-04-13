package com.restapi.practice.service;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.restapi.practice.model.dto.requests.AuthRequest;
import com.restapi.practice.util.JwtUtils;

/**
 * @author Jacinto Escobar Quezada
 * @version 1.0.0
 */

@Service
public class JwtService {
    @Autowired
    private JwtUtils jwtUtils;

    public String generateToken(AuthRequest authRequest) {
        String username = Base64.getEncoder().encodeToString(authRequest.getUsername().getBytes());
        String password = Base64.getEncoder().encodeToString(authRequest.getPassword().getBytes());
        return jwtUtils.generateToken(username, password);
    }

    public DecodedJWT validatesToken(String token) {
        return jwtUtils.validatesToken(token);
    }

    public String getUsername(DecodedJWT decodedJWT) {
        return jwtUtils.getUsername(decodedJWT);
    }

    public String getAuthorities(DecodedJWT decodedJWT) {
        return jwtUtils.getAuthorities(decodedJWT);
    }
}
