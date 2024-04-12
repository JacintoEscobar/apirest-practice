package com.restapi.practice.model.dto.response;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HealthCheckResponse {
    private String timestamp;
    private HttpStatus status;
    private int code;
    private String message;
}

