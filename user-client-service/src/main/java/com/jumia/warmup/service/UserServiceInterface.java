package com.jumia.warmup.service;

import com.jumia.warmup.dto.UserDTO;

/**
 * The interface User service interface.
 */
public interface UserServiceInterface {

    /**
     * Register user.
     *
     * @param userDTO the user dto
     */
    void registerUser(final UserDTO userDTO);
}
