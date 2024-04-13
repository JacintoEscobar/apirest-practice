package com.restapi.practice.model.dto.response;

import lombok.Builder;
import lombok.Getter;

/**
 * @author Jacinto Escobar Quezada
 * @version 1.0.0
 */

@Getter
public class AuthResponse extends ApiResponse {
    private String token;

    @Builder
    public AuthResponse(String timestamp, String message, String token) {
        super(timestamp, message);
        this.token = token;
    }
}
