package com.jumia.warmup;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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

		LOGGER.info("Jumia Services is Awesome");
	}

	/**
	 * Model mapper model mapper.
	 *
	 * @return the model mapper
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
