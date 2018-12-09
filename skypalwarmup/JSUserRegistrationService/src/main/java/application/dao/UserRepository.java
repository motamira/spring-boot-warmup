package application.dao;

import application.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Copyright (c) 2016-2018, Jumia.
 */
public interface UserRepository extends MongoRepository<User, String> {

}
