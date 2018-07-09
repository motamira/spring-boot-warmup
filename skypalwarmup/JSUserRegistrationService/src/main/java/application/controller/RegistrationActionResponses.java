package application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Copyright (c) 2016-2018, Jumia.
 */
public enum RegistrationActionResponses {

    CONFLICT(HttpStatus.CONFLICT, "Attempt to insert user that already exists."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "There was a problem with the request payload. Either a mandatory field "
        + "is missing or a field type is not compliant with the contract."),
    CREATED(HttpStatus.CREATED, "The request was accepted and the resource (user) correctly created.");

    public HttpStatus httpStatus;
    public String statusMessage;

    RegistrationActionResponses(HttpStatus httpStatus, String statusMessage) {
        this.httpStatus = httpStatus;
        this.statusMessage = statusMessage;
    }

    public ResponseEntity<String> getResponseEntity() {
        return ResponseEntity.status(httpStatus).body(statusMessage);
    }
}
