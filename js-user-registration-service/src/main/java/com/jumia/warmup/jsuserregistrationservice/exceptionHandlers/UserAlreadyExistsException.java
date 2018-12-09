package com.jumia.warmup.jsuserregistrationservice.exceptionHandlers;

import com.jumia.warmup.jsuserregistrationservice.utils.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String message) {

        super(Constants.ExceptionHandlerMessages.USER_ALREADY_EXISTS + message);
    }
}
