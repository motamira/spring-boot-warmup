package com.jumia.warmup.jsuserregistrationclient.services;

import com.jumia.warmup.jsuserregistrationclient.dtos.UserDTO;

/**
 * Copyright (c) 2016 - 2018, Jumia.
 */
public interface UserServiceInterface {

    void sendUser(final UserDTO userDTO);
}