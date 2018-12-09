package com.jumia.warmup.service;

import com.jumia.warmup.dto.UserDTO;

/**
 * The interface User service.
 */
public interface IUserService {

    /**
     * Register user.
     *
     * @param userDTO the user dto
     */
    void registerUser(final UserDTO userDTO);
}
