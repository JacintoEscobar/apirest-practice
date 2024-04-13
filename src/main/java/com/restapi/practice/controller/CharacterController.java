package com.restapi.practice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.practice.err.ErrorResponse;
import com.restapi.practice.err.ErrorResponseMessage;
import com.restapi.practice.service.MarvelApiService;
import com.restapi.practice.util.TimeUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Jacinto Escobar Quezada
 * @version 1.0.0
 */

@RestController
@RequestMapping("/character")
public class CharacterController {
    @Autowired
    private TimeUtils timeUtils;

    @Autowired
    MarvelApiService marvelApiService;

    @GetMapping("/{characterId}")
    public ResponseEntity<?> characterId(@PathVariable long characterId) {
        if (characterId == 0) {
            return new ResponseEntity<>(new ErrorResponse(timeUtils.getTimestamp(), ErrorResponseMessage.BAD_REQUEST, "character id is required"), HttpStatus.BAD_REQUEST);
        }
        return marvelApiService.characterId(characterId);
    }
}
