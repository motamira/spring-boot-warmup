package com.jumia.warmup.jsuserregistrationservice.exceptionHandlers;

import com.jumia.warmup.jsuserregistrationservice.utils.Constants;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex,
        HttpHeaders headers,
        HttpStatus status,
        WebRequest request) {

        List<ExceptionResponseDetail> exceptionResponseDetails = ex.getBindingResult().getFieldErrors().stream()
            .map(fieldError -> new ExceptionResponseDetail(fieldError.getField(), fieldError.getDefaultMessage()))
            .collect(Collectors.toList());

        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.BAD_REQUEST,
            Constants.VALIDATION_FAILED,
            exceptionResponseDetails);

        return handleExceptionInternal(
            ex, exceptionResponse, headers, exceptionResponse.getStatus(), request);
    }
}
