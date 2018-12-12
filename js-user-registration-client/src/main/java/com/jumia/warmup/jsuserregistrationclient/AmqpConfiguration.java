package com.jumia.warmup.jsuserregistrationclient;

import com.jumia.warmup.jsuserregistrationclient.utils.Constants;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;


/**
 * Copyright (c) 2016 - 2018, Jumia.
 */
@Configuration
@EnableRabbit
public class AmqpConfiguration implements RabbitListenerConfigurer {

    @Value(Constants.$_SPRING_RABBITMQ_QUEUE)
    private String USERS_QUEUE;

    @Value(Constants.$_SPRING_RABBITMQ_EXCHANGE)
    private String USERS_EXCHANGE;

    @Value(Constants.$_SPRING_RABBITMQ_ROUTING_KEY)
    private String USERS_ROUTING_KEY;

    @Value(Constants.$_SPRING_RABBITMQ_DEAD_QUEUE)
    private String USERS_DEAD_QUEUE;

    @Value(Constants.$_SPRING_RABBITMQ_DEAD_EXCHANGE)
    String USERS_DEAD_EXCHANGE;

    @Bean
    Queue queue() {
        return QueueBuilder.durable(USERS_QUEUE)
            .withArgument(Constants.X_DEAD_LETTER_EXCHANGE, USERS_DEAD_EXCHANGE)
            .withArgument(Constants.X_DEAD_LETTER_ROUTING_KEY, USERS_DEAD_QUEUE)
            .build();
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(USERS_EXCHANGE);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(USERS_ROUTING_KEY);
    }

    @Bean
    Queue deadLetterQueue() {
        return QueueBuilder.durable(USERS_DEAD_QUEUE)
            .withArgument(Constants.X_DEAD_LETTER_EXCHANGE, USERS_DEAD_EXCHANGE)
            .build();
    }

    @Bean
    FanoutExchange deadLetterExchange() {
        return new FanoutExchange(USERS_DEAD_EXCHANGE);
    }

    @Bean
    Binding deadLetterBinding(final Queue deadLetterQueue, final FanoutExchange deadLetterExchange) {
        return BindingBuilder.bind(deadLetterQueue).to(deadLetterExchange);
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory();
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter
        jackson2JsonMessageConverter) {

        final SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jackson2JsonMessageConverter);
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);

        return factory;
    }

    @Bean
    public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {

        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        return factory;
    }

    @Override
    public void configureRabbitListeners(final RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

        rabbitListenerEndpointRegistrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }
}

