package com.jumia.warmup.jsuserregistrationclient.listeners;

import com.jumia.warmup.jsuserregistrationclient.dtos.UserDTO;
import com.jumia.warmup.jsuserregistrationclient.services.UserService;
import com.rabbitmq.client.Channel;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * Copyright (c) 2016 - 2018, Jumia.
 */
@Component
public class UserMessageListener {

    static final Logger LOG = LoggerFactory.getLogger(UserMessageListener.class);

    @Autowired
    Validator validator;

    @Autowired
    private UserService userService;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void processUserDTO(@Valid @Payload UserDTO userDTO, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {

        try {
            Set<ConstraintViolation<UserDTO>> errors = this.validator.validate(userDTO);

            if (!errors.isEmpty()) {

                LOG.info("Validation errors!");

                channel.basicAck(tag, false);
            }

            LOG.info("================================== UserDTO Received: " + userDTO.toString());

            userService.sendUser(userDTO);
            channel.basicAck(tag, true);

        } catch (Exception e) {
            LOG.info("Channel exception");

        }
    }
}