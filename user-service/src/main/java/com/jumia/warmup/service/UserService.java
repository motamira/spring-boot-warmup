package com.jumia.warmup.service;

import com.jumia.warmup.dto.UserDTO;
import com.jumia.warmup.entity.User;
import com.jumia.warmup.exception.UserALreadyExistException;
import com.jumia.warmup.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * The type User service.
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void registerUser(final UserDTO userDTO) throws UserALreadyExistException {

        User existingUser = userRepository.findByUserName(userDTO.getAccountInformationDTO().getUserName());

        if(Objects.isNull(existingUser)) {

            User user = modelMapper.map(userDTO, User.class);

            userRepository.save(user);
        } else {

            throw new UserALreadyExistException();
        }
    }
}