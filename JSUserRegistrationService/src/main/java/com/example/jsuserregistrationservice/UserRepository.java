package com.example.jsuserregistrationservice;

/**
 * Copyright (c) 2016, 2017, Jumia.
 */
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}
