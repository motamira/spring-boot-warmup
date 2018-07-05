package application;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Copyright (c) 2016-2018, Jumia.
 */

@SpringBootApplication
public class Application {
    private static final Log logger = LogFactory.getLog(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        logger.info("Jumia Services is Awesome!! <3");
    }
}
