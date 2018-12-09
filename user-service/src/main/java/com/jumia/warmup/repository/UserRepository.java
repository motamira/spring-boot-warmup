package com.jumia.warmup.repository;

import com.jumia.warmup.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * The interface User repository.
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

    /**
     * Find by user name user.
     *
     * @param userName the user name
     * @return the user
     */
    @Query("{'account_information.user_name': ?0}")
    User findByUserName(final String userName);
}
