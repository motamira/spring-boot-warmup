package com.task.jumia.repositories;

import com.task.jumia.payloads.users.UserDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Copyright (c) 2016, 2017, Jumia.
 */
@Repository
public interface UserDTORepository extends MongoRepository<UserDTO, Long> {
}
