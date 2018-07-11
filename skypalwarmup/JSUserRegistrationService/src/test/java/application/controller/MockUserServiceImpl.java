package application.controller;

import application.entity.User;
import application.service.UserService;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

/**
 * Copyright (c) 2016-2018, Jumia.
 */
@Service
public class MockUserServiceImpl implements UserService {

    private ArrayList<User> users = new ArrayList<>();

    @Override
    public Boolean register(User user) {
        System.out.println("registering *******************************************");
        if (users.contains(user)) {
            return false;
        } else {
            users.add(user);
            return true;
        }
    }

    public boolean exists(User user) {
        return users.contains(user);
    }

    public void deleteAll() {
        users = new ArrayList<>();
    }
}
