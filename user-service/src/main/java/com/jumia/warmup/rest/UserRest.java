package com.jumia.warmup.rest;

import com.jumia.warmup.dto.UserDTO;
import com.jumia.warmup.exception.UserALreadyExistException;
import com.jumia.warmup.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type User rest.
 */
@RestController
@RequestMapping("/api")
public class UserRest {

    @Autowired
    private UserService userService;

    /**
     * Register user.
     *
     * @param userDTO the user dto
     * @throws UserALreadyExistException the user a lready exist exception
     */
    @PostMapping(
            name = "/users",
            value = "/users",
            produces={ MediaType.APPLICATION_JSON_VALUE },
            consumes={ MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(code = HttpStatus.CREATED, value = HttpStatus.CREATED)
    public void registerUser(@Valid @RequestBody UserDTO userDTO) throws UserALreadyExistException {

            userService.registerUser(userDTO);
    }
}
