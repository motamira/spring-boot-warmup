package application.messaging.configuration;

import application.messaging.listener.ConsumerListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Copyright (c) 2016-2018, Jumia.
 */

@Slf4j
@Configuration
public class AmqpBindingConfiguration {


    @Value("${amqp.incoming.queueName}")
    String queueName;

    @Value("${amqp.incoming.exchangeName}")
    String exchangeName;

    @Value("${amqp.incoming.routingKeyName}")
    String routingKeyName;

    @Value("${amqp.deadletter.queueName}")
    String deadletterQueueName;

    @Value("${amqp.deadletter.exchangeName}")
    String deadletterExchangeName;

    @Bean
    public TopicExchange incomingExchange(RabbitAdmin rabbitAdmin) {
        final TopicExchange exchange = new TopicExchange(exchangeName, true, false);
        log.info("Declaring exchange: {}", exchange.getName());
        rabbitAdmin.declareExchange(exchange);
        return exchange;
    }

    @Bean
    public Queue incomingQueue(RabbitAdmin rabbitAdmin) {
        final Queue queue = QueueBuilder.durable(queueName).withArgument("x-dead-letter-exchange", deadletterExchangeName).build();
        log.info("Declaring queue: {}", queue.getName());
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    public Binding incomingBinding(RabbitAdmin rabbitAdmin, TopicExchange exchange, @Qualifier("incomingQueue") Queue queue) {
        final Binding binding = BindingBuilder.bind(queue).to(exchange).with(routingKeyName);
        log.info("Declaring binding: {} -> {}", binding.getExchange(), binding.getDestination());
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public FanoutExchange deadletterExchange(RabbitAdmin rabbitAdmin) {
        final FanoutExchange exchange = new FanoutExchange(deadletterExchangeName, true, false);
        log.info("Declaring exchange: {}", exchange.getName());
        rabbitAdmin.declareExchange(exchange);
        return exchange;
    }

    @Bean
    public Queue deadletterQueue(RabbitAdmin rabbitAdmin) {
        final Queue queue = new Queue(deadletterQueueName, true, false, false);
        log.info("Declaring queue: {}", queue.getName());
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    public Binding deadletterBinding(RabbitAdmin rabbitAdmin, FanoutExchange exchange, @Qualifier("deadletterQueue") Queue queue) {
        final Binding binding = BindingBuilder.bind(queue).to(exchange);
        log.info("Declaring binding: {} -> {}", binding.getExchange(), binding.getDestination());
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public ConsumerListener consumerListener() {
        return new ConsumerListener();
    }
}
