package application.messaging.producer;

import commons.dto.AccountInformationDTO;
import commons.dto.ContactsDTO;
import commons.dto.PersonalDetailsDTO;
import commons.dto.UserInputDTO;
import java.util.Random;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class Producer {

    private final RabbitTemplate rabbitTemplate;

    private final TopicExchange topicExchange;

    @Value("${amqp.incoming.routingKeyName}")
    private String routingKeyName;

    /**
     * Send messages to rabbitmq instance to register new users each 5 seconds.
     */
    @Scheduled(fixedDelay = 5000L, initialDelay = 5000L)
    public void send() {
        AccountInformationDTO inTestAccountInformationDTO = new AccountInformationDTO();
        inTestAccountInformationDTO.setUserName("gadfg" + Math.abs(new Random().nextInt(1000)));
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
