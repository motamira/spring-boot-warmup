package com.jumia.warmup.rest;

import com.jumia.warmup.dto.UserDTO;
import com.jumia.warmup.exception.UserAlreadyExistException;
import com.jumia.warmup.service.UserService;
import com.jumia.warmup.util.Constants;
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
 * The type User rest controller.
 */
@RestController
@RequestMapping(Constants.USER_REST)
public class UserRestController {

    @Autowired
    private UserService userService;

    /**
     * Register user.
     *
     * @param userDTO the user dto
     * @throws UserAlreadyExistException the user a lready exist exception
     */
    @PostMapping(
        name = Constants.USER_REST_REGISTER_USERS,
        value = Constants.USER_REST_REGISTER_USERS,
        produces = {MediaType.APPLICATION_JSON_VALUE},
        consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(code = HttpStatus.CREATED, value = HttpStatus.CREATED)
    public void registerUser(@Valid @RequestBody UserDTO userDTO) throws UserAlreadyExistException {

        userService.registerUser(userDTO);
    }
}
