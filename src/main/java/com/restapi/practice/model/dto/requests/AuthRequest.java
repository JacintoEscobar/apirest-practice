package com.restapi.practice.model.dto.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Jacinto Escobar Quezada
 * @version 1.0.0
 */

@Data
@AllArgsConstructor
public class AuthRequest {
    @NotBlank(message = "username is required")
    private String username;

    @NotBlank(message = "password is required")
    private String password;
}
