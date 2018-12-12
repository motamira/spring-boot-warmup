package com.jumia.warmup.service;

import com.jumia.warmup.dto.UserDTO;
import com.jumia.warmup.exception.UserAlreadyExistException;

/**
 * The interface User service interface.
 */
public interface UserServiceInterface {

    /**
     * Register user.
     *
     * @param userDTO the user dto
     * @throws UserAlreadyExistException the user a lready exist exception
     */
    void registerUser(final UserDTO userDTO) throws UserAlreadyExistException;

}
