package application.service;

import application.messaging.dto.UserInputDTO;

/**
 * Copyright (c) 2016-2018, Jumia.
 */
public interface RestConsumer {
    boolean postUserRegistration(UserInputDTO userInputDTO);
}
