package application.service;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Copyright (c) 2016-2018, Jumia.
 */
@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
