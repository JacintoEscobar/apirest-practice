package com.restapi.practice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.practice.err.ErrorResponse;
import com.restapi.practice.err.ErrorResponseMessage;
import com.restapi.practice.model.dto.auth.AuthResponseMessage;
import com.restapi.practice.model.dto.requests.AuthRequest;
import com.restapi.practice.model.dto.response.AuthResponse;
import com.restapi.practice.service.JwtService;
import com.restapi.practice.util.RequestValidation;
import com.restapi.practice.util.TimeUtils;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Jacinto Escobar Quezada
 * @version 1.0.0
 */

@RestController
@RequestMapping("/auth")
public class AuthController implements AuthResponseMessage {
    @Autowired
    private TimeUtils timeUtils;

    @Autowired
    private RequestValidation requestValidation;

    @Autowired
    private JwtService jwtService;

    @GetMapping
    public ResponseEntity<?> auth(@RequestBody @Valid AuthRequest authRequest, BindingResult validation) {
        try {
            if (requestValidation.invalidRequest(validation)) {
                return new ResponseEntity<>(new ErrorResponse(timeUtils.getTimestamp(), ErrorResponseMessage.BAD_REQUEST, requestValidation.getErrors(validation)), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(new ErrorResponse(timeUtils.getTimestamp(), ErrorResponseMessage.INTERNAL_SERVER_ERROR, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        String token = jwtService.generateToken(authRequest);
        return new ResponseEntity<>(new AuthResponse(timeUtils.getTimestamp(), AuthResponseMessage.AUTHENTICATED, token), HttpStatus.OK);
    }
}
