package com.jumia.warmup.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumia.warmup.dto.AccountInformationDTO;
import com.jumia.warmup.dto.ContactsDTO;
import com.jumia.warmup.dto.PersonalDetailsDTO;
import com.jumia.warmup.dto.UserDTO;
import javax.annotation.PostConstruct;
import org.springframework.amqp.core.Message;
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
@Profile("dev")
public class UserRegistrationValidProducer {

    @Value("${rabbitmq.topic.exchange.name}")
    private String topicExchangeName;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private UserDTO userDTO;

    @PostConstruct
    public void generateInvalidUserDTO() {

        userDTO = new UserDTO(
            new PersonalDetailsDTO("Mohammed", "Hanfy", 27),
            new AccountInformationDTO("mhanfy", "M_Hanfy_7"),
            new ContactsDTO("+20 1275284823", "mohammed.ahmed.hanfy@gmail.com")
        );
    }

    /**
     * Register user.
     */
    @Scheduled(fixedDelay = 50000l)
    public void registerUser() {

        this.rabbitTemplate.convertAndSend(topicExchangeName, routingKey, userDTO);
    }
}
