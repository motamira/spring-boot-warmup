package application.service;

import application.dao.UserRepository;
import application.dto.UserInputDTO;
import application.entity.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (c) 2016-2018, Jumia.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Log logger = LogFactory.getLog(UserServiceImpl.class);

    @Autowired
    UserRepository userRepository;

    @Override
    public Boolean register(UserInputDTO userInputDTO) {
        User user = mapFromUserDTO(userInputDTO);
        try {
            insertUser(user);
            return true;
        } catch (org.springframework.dao.DuplicateKeyException e) {
            return false;
        }

    }

    private User mapFromUserDTO(UserInputDTO userInputDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userInputDTO, User.class);
    }

    private void insertUser(User user) {
        userRepository.save(user);
    }
}
