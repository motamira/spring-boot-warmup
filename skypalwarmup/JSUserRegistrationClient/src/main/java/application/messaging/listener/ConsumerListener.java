package application.messaging.listener;

import application.messaging.dto.UserInputDTO;
import java.util.Random;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * Copyright (c) 2016-2018, Jumia.
 */

@Slf4j
@Component
public class ConsumerListener {

    @RabbitListener(queues = "${amqp.incoming.queueName}")
    public void onMessage(@Valid @Payload UserInputDTO userInputDTO) throws AmqpRejectAndDontRequeueException {
        log.info("Received registration for: {}", userInputDTO.getAccountInformation().getUserName());
        if (new Random().nextBoolean()) {
            throw new AmqpRejectAndDontRequeueException("Rejecting user registration");
        }
    }
}
