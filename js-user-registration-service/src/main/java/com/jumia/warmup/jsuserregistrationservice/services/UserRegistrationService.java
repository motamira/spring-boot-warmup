package com.jumia.warmup.jsuserregistrationservice.services;

import com.jumia.warmup.jsuserregistrationservice.JsUserRegistrationServiceApplication;
import com.jumia.warmup.jsuserregistrationservice.dtos.UserDTO;
import com.jumia.warmup.jsuserregistrationservice.entities.User;
import com.jumia.warmup.jsuserregistrationservice.exceptionHandlers.UserAlreadyExistsException;
import com.jumia.warmup.jsuserregistrationservice.repositories.UserRepository;
import com.jumia.warmup.jsuserregistrationservice.utils.Constants;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService implements IUserRegistrationService {

    static final Logger LOG = LoggerFactory.getLogger(JsUserRegistrationServiceApplication.class);

    @Autowired
    final UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    public UserRegistrationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(UserDTO userDTO) {

        Optional.ofNullable(
            userRepository.findByUserName(userDTO.getAccountInformationDTO().getUserName()))
            .flatMap(
                u -> {
                    throw new UserAlreadyExistsException(userDTO.getAccountInformationDTO().getUserName());
                })
            .orElseGet(
                () -> {
                    User user = modelMapper.map(userDTO, User.class);

                    userRepository.save(user);

                    LOG.info(Constants.LOG.REGISTERED_USER + user.toString());

                    return user;
                }
            );
    }
}
