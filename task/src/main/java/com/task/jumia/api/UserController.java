package com.task.jumia.api;
import com.task.jumia.exceptions.UserAlreadyExistsException;
import com.task.jumia.payloads.users.UserDTO;
import com.task.jumia.services.UserDTOService;
import com.task.jumia.services.UserDTOServiceI;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright (c) 2016, 2017, Jumia.
 */
@RestController
@RequestMapping(path = "api/users")
public class UserController {

    @Autowired
    private UserDTOService userService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    UserDTO add(@RequestBody @Valid UserDTO input) {
        try {
            return this.userService.createUser(input);
        } catch (DuplicateKeyException e) {
            throw new UserAlreadyExistsException();
        }
    }
}
