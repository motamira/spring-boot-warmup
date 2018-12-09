package com.jumia.warmup.jsuserregistrationservice.services;

import com.jumia.warmup.jsuserregistrationservice.dtos.UserDTO;

public interface IUserRegistrationService {

    void registerUser(UserDTO userDTO);
}
