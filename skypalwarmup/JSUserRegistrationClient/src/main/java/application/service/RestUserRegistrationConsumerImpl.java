package application.service;

import application.messaging.dto.UserInputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * Copyright (c) 2016-2018, Jumia.
 */
public class RestUserRegistrationConsumerImpl implements RestConsumer {

    @Autowired
    private RestTemplate restTemplate;

    public void postUserRegistration(UserInputDTO userInputDTO) {
        try {
            System.out.println(restTemplate.postForObject("http://localhost:8080/api/register", userInputDTO, String.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
