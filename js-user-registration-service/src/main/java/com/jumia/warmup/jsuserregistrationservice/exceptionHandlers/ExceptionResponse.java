package com.jumia.warmup.jsuserregistrationservice.exceptionHandlers;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ExceptionResponse {

    private HttpStatus status;
    private String message;
    private List<ExceptionResponseDetail> details;

    @Builder
    public ExceptionResponse(HttpStatus status, String message, List<ExceptionResponseDetail> details) {
        this.status = status;
        this.message = message;
        this.details = details;
    }
}
