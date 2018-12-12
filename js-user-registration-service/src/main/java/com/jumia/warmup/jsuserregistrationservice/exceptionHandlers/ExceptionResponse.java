package com.jumia.warmup.jsuserregistrationservice.exceptionHandlers;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
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
}
