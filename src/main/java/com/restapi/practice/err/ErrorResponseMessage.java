package com.restapi.practice.err;

/**
 * @author Jacinto Escobar Quezada
 * @version 1.0.0
 */

public abstract interface ErrorResponseMessage {
    public final String BAD_REQUEST = "bad request was receive";
    public final String INVALID_CREDENTIALS = "credentials are invalid or bad formed";
    public final String INTERNAL_SERVER_ERROR = "an error has ocurred";
}
