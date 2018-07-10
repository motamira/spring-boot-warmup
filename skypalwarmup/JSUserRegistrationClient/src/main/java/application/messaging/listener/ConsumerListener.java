package application.messaging.listener;

import application.messaging.dto.UserInputDTO;
import application.service.RestConsumer;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * Copyright (c) 2016-2018, Jumia.
 */

@Slf4j
@Component
public class ConsumerListener {

    @Autowired
    private RestConsumer restConsumer;

    @RabbitListener(queues = "${amqp.incoming.queueName}")
    public void onMessage(@Valid @Payload UserInputDTO userInputDTO) throws AmqpRejectAndDontRequeueException {
        log.info("Received registration for: {}", userInputDTO.getAccountInformation().getUserName());
        if (restConsumer.postUserRegistration(userInputDTO)) {
            log.info("User created: {}", userInputDTO.toString());
        } else {
            throw new AmqpRejectAndDontRequeueException("Rejecting user registration");
        }
    }
}
