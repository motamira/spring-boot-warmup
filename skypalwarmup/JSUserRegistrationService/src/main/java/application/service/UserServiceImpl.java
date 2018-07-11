package application.service;

import application.dao.UserRepository;
import application.entity.User;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (c) 2016-2018, Jumia.
 */
@Service
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class UserServiceImpl implements UserService {

    private static final Log logger = LogFactory.getLog(UserServiceImpl.class);

    private final UserRepository userRepository;

    @Override
    public Boolean register(User user) {
        try {
            insertUser(user);
            return true;
        } catch (org.springframework.dao.DuplicateKeyException e) {
            return false;
        }

    }

    private void insertUser(User user) {
        userRepository.save(user);
    }
}
