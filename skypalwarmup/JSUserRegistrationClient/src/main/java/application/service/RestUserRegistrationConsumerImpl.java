package application.service;

import application.messaging.dto.UserInputDTO;
import java.net.URI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Copyright (c) 2016-2018, Jumia.
 */

@Slf4j
@Service
public class RestUserRegistrationConsumerImpl implements RestConsumer {

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * Create a new post request to register the user information with the
     * user registration service.
     *
     * @param userInputDTO the user registration information
     * @return true in case user was created successfully / false otherwise
     */
    public boolean postUserRegistration(UserInputDTO userInputDTO) {
        try {
            ResponseEntity<String> response = restTemplate.exchange(RequestEntity
                .post(new URI("http://localhost:8080/api/users"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.TEXT_PLAIN)
                .body(userInputDTO), String.class);
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            return false;
        }
    }
}