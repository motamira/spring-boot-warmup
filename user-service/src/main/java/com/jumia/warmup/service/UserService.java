package com.jumia.warmup.service;

import com.jumia.warmup.dto.UserDTO;
import com.jumia.warmup.entity.User;
import com.jumia.warmup.exception.UserAlreadyExistException;
import com.jumia.warmup.repository.UserRepositoryInterface;
import com.jumia.warmup.util.Constants;
import java.util.Objects;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type User service.
 */
@Service
public class UserService implements UserServiceInterface {

    static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepositoryInterface userRepositoryInterface;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void registerUser(final UserDTO userDTO) throws UserAlreadyExistException {

        LOGGER.info(Constants.USER_PAYLOAD + userDTO);

        User existingUser = userRepositoryInterface.findByUserName(userDTO.getAccountInformationDTO().getUserName());

        if (Objects.isNull(existingUser)) {

            User user = modelMapper.map(userDTO, User.class);

            userRepositoryInterface.save(user);
        } else {

            throw new UserAlreadyExistException();
        }
    }
}
