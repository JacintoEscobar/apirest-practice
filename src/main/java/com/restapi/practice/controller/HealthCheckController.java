package com.restapi.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.practice.model.dto.healthCheck.HealthCheckResponseMessage;
import com.restapi.practice.model.dto.healthCheck.HealthCheckStates;
import com.restapi.practice.model.dto.response.HealthCheckResponse;
import com.restapi.practice.util.time.TimeUtils;

/**
 * @author Jacinto Escobar Quezada
 * @version 1.0.0
 */

@RestController
@RequestMapping("/health-check")
public class HealthCheckController {
    @Autowired
    private TimeUtils timeUtils;

    @GetMapping
    public ResponseEntity<?> healthCheck() {
        HealthCheckResponse healthCheckResponse = new HealthCheckResponse(timeUtils.getTimestamp(), HealthCheckResponseMessage.OK, HealthCheckStates.UP);
        return new ResponseEntity<>(healthCheckResponse, HttpStatus.OK);
    }
}
