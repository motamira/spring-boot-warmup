package application.service;

import commons.dto.UserInputDTO;

/**
 * Copyright (c) 2016-2018, Jumia.
 */
public interface UserService {

    Boolean register(UserInputDTO user);
}
