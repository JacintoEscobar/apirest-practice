package com.restapi.practice.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Jacinto Escobar Quezada
 * @version 1.0.0
 */

@Data
@AllArgsConstructor
public class ApiResponse {
    private String timestamp;
    private String message;
}
