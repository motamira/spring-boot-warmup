package com.jumia.warmup;

import com.jumia.warmup.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The type User service application.
 */
@SpringBootApplication
public class UserServiceApplication {

    private static Logger LOGGER = LoggerFactory.getLogger(UserServiceApplication.class);

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);

        LOGGER.info(Constants.WELCOME_MESSAGE);
    }
}
