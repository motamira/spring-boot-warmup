package com.task.jumia.services;

import com.task.jumia.payloads.users.UserDTO;
import com.task.jumia.repositories.UserDTORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (c) 2016, 2017, Jumia.
 */
@Service
public class UserDTOService implements UserDTOServiceI {

    @Autowired
    private UserDTORepository repo;

    public UserDTO createUser(UserDTO inputUser) {
        return this.repo.save(inputUser);
    }
}
