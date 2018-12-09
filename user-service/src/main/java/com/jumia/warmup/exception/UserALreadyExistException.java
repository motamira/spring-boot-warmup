package com.jumia.warmup.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type User a lready exist exception.
 */
@ResponseStatus(code = HttpStatus.CONFLICT, value = HttpStatus.CONFLICT, reason = "User with this userName already exist.")
public class UserALreadyExistException extends RuntimeException {

}
