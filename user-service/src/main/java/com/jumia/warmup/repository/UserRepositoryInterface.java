package com.jumia.warmup.repository;

import com.jumia.warmup.entity.User;
import com.jumia.warmup.util.Constants;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * The interface User repository.
 */
@Repository
public interface UserRepositoryInterface extends MongoRepository<User, String> {

    /**
     * Find by user name user.
     *
     * @param userName the user name
     * @return the user
     */
    @Query(Constants.FIND_BY_USERNAME)
    User findByUserName(final String userName);
}
