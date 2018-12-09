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

    @Value("${spring.rabbitmq.queue}")
    private String USERS_QUEUE;

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public UserMessageSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedDelay = 5000)
    public void sendUser() {

        UserDTO userDTO = new UserDTO(new AccountInformationDTO("sara*&&%%&&Test", "aaZZa44@")
            , new ContactsDTO("01234774774", "saraSalah@gmail.com")
            , new PersonalDetailsDTO("Sara", "Salah", 20));

        LOG.info(">>>>>>>>>>>>>>>>>>>>> Sending example object at " + userDTO.toString());

        this.rabbitTemplate.convertAndSend(USERS_QUEUE, userDTO);
    }
}
