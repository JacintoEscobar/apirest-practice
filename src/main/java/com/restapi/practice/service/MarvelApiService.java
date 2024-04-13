package com.restapi.practice.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.restapi.practice.err.ErrorResponse;
import com.restapi.practice.err.ErrorResponseMessage;
import com.restapi.practice.util.ApiHashBuilder;
import com.restapi.practice.util.TimeUtils;

@Service
public class MarvelApiService extends ApiHashBuilder {
    @Value("${API_URL}")
    private String API_URL;

    @Value("${PUBLIC_KEY}")
    private String PUBLIC_KEY;

    @Value("${PRIVATE_KEY}")
    private String PRIVATE_KEY;

    @Autowired
    TimeUtils timeUtils;

    private RestTemplate restTemplate;

    public MarvelApiService() {
        this.restTemplate = new RestTemplate();
    }

    public ResponseEntity<?> characterId(long characterId) {
        String ts = String.valueOf(System.currentTimeMillis());
        String hash = this.makeHash(ts, PRIVATE_KEY, PUBLIC_KEY);
        String uri = API_URL + "/characters/" + characterId + "?ts=" + ts + "&apikey=" + PUBLIC_KEY + "&hash=" + hash;

        HashMap<?, ?> response = new HashMap<>();
        try {
            response = restTemplate.getForObject(uri, new HashMap<String, Object>().getClass());
        } catch (RestClientException ex) {
            return new ResponseEntity<>(new ErrorResponse(timeUtils.getTimestamp(), ErrorResponseMessage.INTERNAL_SERVER_ERROR, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> comicId(long comicId) {
        String ts = String.valueOf(System.currentTimeMillis());
        String hash = this.makeHash(ts, PRIVATE_KEY, PUBLIC_KEY);
        String uri = API_URL + "/comics/" + comicId + "?ts=" + ts + "&apikey=" + PUBLIC_KEY + "&hash=" + hash;

        HashMap<?, ?> response = new HashMap<>();
        try {
            response = restTemplate.getForObject(uri, new HashMap<String, Object>().getClass());
        } catch (RestClientException ex) {
            return new ResponseEntity<>(new ErrorResponse(timeUtils.getTimestamp(), ErrorResponseMessage.INTERNAL_SERVER_ERROR, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
