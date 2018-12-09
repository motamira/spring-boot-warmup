package com.jumia.warmup.jsuserregistrationclient.listeners;

import com.jumia.warmup.jsuserregistrationclient.dtos.UserDTO;
import com.jumia.warmup.jsuserregistrationclient.services.UserService;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * Copyright (c) 2016 - 2018, Jumia.
 */
@Component
public class UserMessageListener {

    static final Logger LOG = LoggerFactory.getLogger(UserMessageListener.class);

    @Autowired
    private UserService userService;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void processUserDTO(@Valid @Payload UserDTO userDTO) {
        try {
            LOG.info("================================== UserDTO Received: " + userDTO.toString());

//            userService.sendUser(userDTO);

        } catch (Exception f) {
        }

    }
}
