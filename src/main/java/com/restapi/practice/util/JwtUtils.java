package com.restapi.practice.util;

import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * @author Jacinto Escobar Quezada
 * @version 1.0.0
 */

@Component
public class JwtUtils {
    @Value("${SECRET}")
    private String SECRET;

    @Value("${USER_GENERATOR}")
    private String USER_GENERATOR;

    private final long EXPIRATION_TIME = 960000;

    public String generateToken(String username, String password) {
        return JWT
                .create()
                .withIssuer(USER_GENERATOR)
                .withSubject(username)
                .withClaim("authorities", "READ")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .withJWTId(UUID.randomUUID().toString())
                .withNotBefore(new Date(System.currentTimeMillis()))
                .sign(Algorithm.HMAC256(Base64.getEncoder().encodeToString(SECRET.getBytes())));
    }

    public DecodedJWT validatesToken(String token) {
        try {
            JWTVerifier jwtVerifier = JWT
                    .require(Algorithm.HMAC256(Base64.getEncoder().encodeToString(SECRET.getBytes())))
                    .withIssuer(USER_GENERATOR)
                    .build();
            DecodedJWT decodedToken = jwtVerifier.verify(token);
            return decodedToken;
        } catch (JWTVerificationException ex) {
            throw new JWTVerificationException("invalid token: " + ex.getMessage());
        }
    }

    public String getUsername(DecodedJWT decodedJWT) {
        return decodedJWT.getSubject().toString();
    }

    public String getAuthorities(DecodedJWT decodedJWT) {
        return decodedJWT.getClaim("authorities").toString();
    }
}
