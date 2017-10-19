package com.jumia.client.config;

import com.jumia.client.listeners.Listener;
import java.util.HashMap;
import java.util.Map;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Copyright (c) 2016, 2017, Jumia.
 */
@Configuration
public class AmqpConfiguration {

    @Value("${rabbit.host}")
    protected String host;

    @Value("${rabbit.username}")
    protected String username;

    @Value("${rabbit.password}")
    protected String password;

    @Value("${rabbit.queueName}")
    protected String queueName;

    @Value("${rabbit.routingKey}")
    protected String routingKey;

    @Value("${rabbit.topicExchangeName}")
    protected String topicExchangeName;

    @Value("${rabbit.fanoutDeadletterExchangeName}")
    protected String fanoutDeadletterExchangeName;

    @Value("${rabbit.deadletterQueueName}")
    protected String deadletterQueueName;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(this.host);
        connectionFactory.setUsername(this.username);
        connectionFactory.setPassword(this.password);
        return connectionFactory;
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(this.routingKey);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(this.topicExchangeName);
    }

    @Bean
    public Queue queue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", this.fanoutDeadletterExchangeName);
        args.put("x-dead-letter-routing-key", this.deadletterQueueName);
        return new Queue(this.queueName, true, false, false, args);
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
        MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(this.queueName);
        container.setMessageListener(listenerAdapter);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return container;
    }

    @Bean
    Listener getListener() {
        return new Listener();
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Listener receiver) {
        MessageListenerAdapter result = new MessageListenerAdapter(receiver, "onMessage");
        return result;
    }

    @Bean
    Queue deadletterQueue() {
        return new Queue(this.deadletterQueueName);
    }

    @Bean
    TopicExchange deadletterExchange() {
        return new TopicExchange(this.fanoutDeadletterExchangeName);
    }

    @Bean
    Binding deadletterBinding() {
        return BindingBuilder.bind(deadletterQueue()).to(deadletterExchange()).with(this.deadletterQueueName);
    }

}
