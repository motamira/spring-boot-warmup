package application.messaging.producer;

import application.messaging.dto.AccountInformationDTO;
import application.messaging.dto.ContactsDTO;
import application.messaging.dto.PersonalDetailsDTO;
import application.messaging.dto.UserInputDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Copyright (c) 2016-2018, Jumia.
 */

@Slf4j
@Component
public class Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TopicExchange topicExchange;

    @Value("${amqp.incoming.routingKeyName}")
    private String routingKeyName;

    @Scheduled(fixedDelay = 1000L, initialDelay = 500L)
    public void send() {
        AccountInformationDTO inTestAccountInformationDTO = new AccountInformationDTO();
        inTestAccountInformationDTO.setUserName("asdfg");
        inTestAccountInformationDTO.setPassword("Aa1$bbb");

        ContactsDTO inTestContactsDTO = new ContactsDTO();
        inTestContactsDTO.setEmail("email@test.co.uk");
        inTestContactsDTO.setCellPhone("+351 910000000");

        PersonalDetailsDTO inTestPersonalDetailsDTO = new PersonalDetailsDTO();
        inTestPersonalDetailsDTO.setAge("25");
        inTestPersonalDetailsDTO.setFirstName("karim");

        UserInputDTO inTest = new UserInputDTO();

        inTest.setAccountInformation(inTestAccountInformationDTO);
        inTest.setContacts(inTestContactsDTO);
        inTest.setPersonalDetails(inTestPersonalDetailsDTO);

        log.info("writing message to rabbitmq");
        rabbitTemplate.convertAndSend(topicExchange.getName(), routingKeyName, inTest);
    }
}
