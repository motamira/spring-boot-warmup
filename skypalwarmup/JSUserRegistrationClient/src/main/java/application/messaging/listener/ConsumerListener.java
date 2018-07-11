package application.messaging.listener;

import application.service.RestConsumer;
import commons.dto.UserInputDTO;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ConsumerListener {

    private final RestConsumer restConsumer;

    /**
     * Handles messages received from the rabbitMq broker, first validating the message is a valid payload of type userInputDTO
     * in case the message is not valid an exception will be thrown and message moved to the deadletter exchange.
     *
     * @param userInputDTO rabbitMq incoming message user information to be sent for registration
     * @throws AmqpRejectAndDontRequeueException in case user registration was unsuccessful
     */
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
