package com.jumia.warmup.jsuserregistrationservice.exceptionHandlers;

import java.util.List;
import org.springframework.http.HttpStatus;

public class ExceptionResponse {

    private HttpStatus status;
    private String message;
    private List<ExceptionResponseDetail> details;

    public ExceptionResponse() {
    }

    public ExceptionResponse(HttpStatus status, String message, List<ExceptionResponseDetail> details) {
        this.status = status;
        this.message = message;
        this.details = details;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List getDetails() {
        return details;
    }

    public void setDetails(List<ExceptionResponseDetail> details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "ExceptionResponse{" +
            "status=" + status +
            ", message='" + message + '\'' +
            ", details=" + details +
            '}';
    }
}
