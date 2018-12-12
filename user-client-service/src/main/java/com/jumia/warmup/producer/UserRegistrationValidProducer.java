package com.jumia.warmup.producer;

import com.jumia.warmup.dto.UserDTO;
import com.jumia.warmup.provider.UserDTOProvider;
import com.jumia.warmup.util.Constants;
import javax.annotation.PostConstruct;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Copyright (c) 2016 - 2018, Jumia.
 */
@Component
@Profile(Constants.DEV_PROFILE)
public class UserRegistrationValidProducer {

    @Value(Constants.TOPIC_EXCHANGE_NAME)
    private String topicExchangeName;

    @Value(Constants.ROUTING_KEY)
    private String routingKey;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private UserDTO userDTO;

    @PostConstruct
    public void generateValidUserDTO() {

        userDTO = UserDTOProvider.getUserDTO();
    }

    /**
     * Register user.
     */
    @Scheduled(fixedDelay = Constants.FIXED_MESSAGE_DELAY)
    public void registerUser() {

        this.rabbitTemplate.convertAndSend(topicExchangeName, routingKey, userDTO);
    }
}
