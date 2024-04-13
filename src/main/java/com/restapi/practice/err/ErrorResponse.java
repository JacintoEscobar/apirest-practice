package com.restapi.practice.err;

import com.restapi.practice.model.dto.response.ApiResponse;

import lombok.Builder;
import lombok.Getter;

/**
 * @author Jacinto Escobar Quezada
 * @version 1.0.0
 */

@Getter
public class ErrorResponse extends ApiResponse {
    private Object error;

    @Builder
    public ErrorResponse(String timestamp, String message, Object error) {
        super(timestamp, message);
        this.error = error;
    }
}
