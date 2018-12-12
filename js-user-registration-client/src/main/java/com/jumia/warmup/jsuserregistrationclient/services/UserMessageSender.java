package com.jumia.warmup.jsuserregistrationclient.services;

import com.jumia.warmup.jsuserregistrationclient.dtos.UserDTO;
import com.jumia.warmup.jsuserregistrationclient.providers.UserDTOProvider;
import com.jumia.warmup.jsuserregistrationclient.utils.Constants;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Copyright (c) 2016 - 2018, Jumia.
 */

@Service
public class UserMessageSender implements UserMessageSenderInterface {

    private final RabbitTemplate rabbitTemplate;

    @Value(Constants.$_SPRING_RABBITMQ_EXCHANGE)
    private String USERS_EXCHANGE;

    @Value(Constants.$_SPRING_RABBITMQ_ROUTING_KEY)
    private String USERS_ROUTING_KEY;

    @Autowired
    public UserMessageSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedDelay = Constants.FIVE_MS)
    public void sendUser() {

        UserDTO userDTO = UserDTOProvider.getUserDTO();

        this.rabbitTemplate.convertAndSend(USERS_EXCHANGE, USERS_ROUTING_KEY, userDTO);

    }
}
