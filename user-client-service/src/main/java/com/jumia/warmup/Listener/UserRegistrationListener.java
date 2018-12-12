package com.jumia.warmup.Listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumia.warmup.dto.UserDTO;
import com.jumia.warmup.service.UserService;
import com.jumia.warmup.util.Constants;
import com.rabbitmq.client.Channel;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Copyright (c) 2016 - 2018, Jumia.
 */
@Component
public class UserRegistrationListener implements ChannelAwareMessageListener {

    /**
     * The Logger.
     */
    static final Logger LOGGER = LoggerFactory.getLogger(UserRegistrationListener.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Validator validator;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        UserDTO userDTO = mapper.readValue(new String(message.getBody()), UserDTO.class);

        Set<ConstraintViolation<UserDTO>> errors = this.validator.validate(userDTO);

        if (!errors.isEmpty()) {

            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }

        LOGGER.info(Constants.USER_PAYLOAD + userDTO.toString());

        userService.registerUser(userDTO);

        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}