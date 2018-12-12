package com.jumia.warmup.exception;

import com.jumia.warmup.util.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type User already exist exception.
 */
@ResponseStatus(code = HttpStatus.CONFLICT, value = HttpStatus.CONFLICT, reason = Constants.USER_ALREADY_EXIST)
public class UserAlreadyExistException extends RuntimeException {

}
