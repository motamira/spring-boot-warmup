package application.messaging.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Copyright (c) 2016-2018, Jumia.
 */

@Configuration
public class JacksonConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        return objectMapper;
    }
}
