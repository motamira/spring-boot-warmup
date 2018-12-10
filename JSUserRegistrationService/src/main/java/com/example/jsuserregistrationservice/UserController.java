package com.example.jsuserregistrationservice;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Copyright (c) 2016, 2017, Jumia.
 */

@RequestMapping("/api/users")
@RestController
public class UserController
{

    @Autowired
    private UserRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getUsers() {
        List<User> result = repository.findAll();
        return result;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity addUser(@Valid @RequestBody User user) {
        try {
            repository.save(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (org.springframework.dao.DuplicateKeyException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
