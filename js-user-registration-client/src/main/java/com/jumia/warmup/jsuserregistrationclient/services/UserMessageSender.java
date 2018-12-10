package com.jumia.warmup.jsuserregistrationclient.services;

import com.jumia.warmup.jsuserregistrationclient.dtos.AccountInformationDTO;
import com.jumia.warmup.jsuserregistrationclient.dtos.ContactsDTO;
import com.jumia.warmup.jsuserregistrationclient.dtos.PersonalDetailsDTO;
import com.jumia.warmup.jsuserregistrationclient.dtos.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Copyright (c) 2016 - 2018, Jumia.
 */

@Service
public class UserMessageSender {

    static final Logger LOG = LoggerFactory.getLogger(UserMessageSender.class);

    @Value("${spring.rabbitmq.exchange}")
    private String USERS_EXCHANGE;

    @Value("${spring.rabbitmq.routing.key}")
    private String USERS_ROUTING_KEY;

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public UserMessageSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedDelay = 5000)
    public void sendUser() {

        UserDTO userDTO = new UserDTO(new AccountInformationDTO("sara&^^&^&Client", "aaZZa44@")
            , new ContactsDTO("01234774774", "saraSalah@gmail.com")
            , new PersonalDetailsDTO("Sara", "Salah", 20));

        this.rabbitTemplate.convertAndSend(USERS_EXCHANGE, USERS_ROUTING_KEY, userDTO);

    }
}
