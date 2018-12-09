package com.jumia.warmup.service;

import com.jumia.warmup.dto.UserDTO;
import com.jumia.warmup.exception.UserALreadyExistException;

/**
 * The interface User service.
 */
public interface IUserService {

    /**
     * Register user.
     *
     * @param userDTO the user dto
     * @throws UserALreadyExistException the user a lready exist exception
     */
    void registerUser(final UserDTO userDTO) throws UserALreadyExistException;

}
