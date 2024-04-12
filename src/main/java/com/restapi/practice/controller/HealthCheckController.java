package com.restapi.practice.controller;

import java.text.SimpleDateFormat;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.practice.model.dto.codes.ResponseStatusCode;
import com.restapi.practice.model.dto.messages.HealthCheckResponseMessage;
import com.restapi.practice.model.dto.response.HealthCheckResponse;

/**
 * @author Jacinto Escobar Quezada
 * @version 1.0.0
 */

@RestController
@RequestMapping("/health-check")
public class HealthCheckController implements HealthCheckResponseMessage, ResponseStatusCode {
    @GetMapping
    public ResponseEntity<?> healthCheck() {
        HealthCheckResponse healthCheckResponse = new HealthCheckResponse(this.getTimestamp(), HttpStatus.OK, ResponseStatusCode.OK, HealthCheckResponseMessage.OK);
        return new ResponseEntity<>(healthCheckResponse, HttpStatus.OK);
    }

    private String getTimestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
    }
}

