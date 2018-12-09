package com.jumia.warmup.jsuserregistrationservice.repositories;

import com.jumia.warmup.jsuserregistrationservice.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    @Query("{'accountInformation.userName': ?0}")
    User findByUserName(final String userName);
}
