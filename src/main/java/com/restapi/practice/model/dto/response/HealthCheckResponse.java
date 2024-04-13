package com.restapi.practice.model.dto.response;

import com.restapi.practice.model.dto.healthCheck.HealthCheckStates;

import lombok.Builder;
import lombok.Getter;

/**
 * @author Jacinto Escobar Quezada
 * @version 1.0.0
 */

@Getter
public class HealthCheckResponse extends ApiResponse {
    private HealthCheckStates healthCheckState;

    @Builder
    public HealthCheckResponse(String timestamp, String message, HealthCheckStates healthCheckState) {
        super(timestamp, message);
        this.healthCheckState = healthCheckState;
    }
}
