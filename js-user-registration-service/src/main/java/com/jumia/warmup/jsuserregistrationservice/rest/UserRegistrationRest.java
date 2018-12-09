package com.jumia.warmup.jsuserregistrationservice.rest;

import com.jumia.warmup.jsuserregistrationservice.dtos.UserDTO;
import com.jumia.warmup.jsuserregistrationservice.services.IUserRegistrationService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/api/users")
public class UserRegistrationRest {

    @Autowired
    IUserRegistrationService userRegistrationService;

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void registerUser(@Valid @RequestBody UserDTO userDTO) {

        userRegistrationService.registerUser(userDTO);
    }
}
