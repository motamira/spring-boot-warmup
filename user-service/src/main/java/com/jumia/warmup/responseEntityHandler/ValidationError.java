package com.jumia.warmup.responseEntityHandler;

import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * The type Validation error.
 */
public class ValidationError implements Serializable {

    private HttpStatus status;
    private String message;
    private List<String> errors;

    /**
     * Instantiates a new Validation error.
     */
    public ValidationError() {
    }

    /**
     * Instantiates a new Validation error.
     *
     * @param status the status
     * @param message the message
     * @param errors the errors
     */
    public ValidationError(HttpStatus status, String message, List<String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    /**
     * Instantiates a new Validation error.
     *
     * @param status the status
     * @param message the message
     * @param error the error
     */
    public ValidationError(HttpStatus status, String message, String error) {
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public HttpStatus getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets errors.
     *
     * @return the errors
     */
    public List<String> getErrors() {
        return errors;
    }

    /**
     * Sets errors.
     *
     * @param errors the errors
     */
    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "ValidationError{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", errors=" + errors +
                '}';
    }
}
