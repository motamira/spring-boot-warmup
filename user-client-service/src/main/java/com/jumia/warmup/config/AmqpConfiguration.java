package com.jumia.warmup.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type Amqp configuration.
 */
@Configuration
public class AmqpConfiguration {

    @Value("${rabbitmq.queue.name}")
    private String queueName;

    @Value("${rabbitmq.topic.exchange.name}")
    private String topicExchangeName;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Value("${rabbitmq.deadletter.queue.name}")
    private String deadletterQueueName;

    @Value("${rabbitmq.deadletter.fanout.exchange.name}")
    private String deadletterFanoutExchangeName;

    /**
     * Queue queue.
     *
     * @return the queue
     */
    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    /**
     * Exchange topic exchange.
     *
     * @return the topic exchange
     */
    @Bean
    TopicExchange exchange() {
        return new TopicExchange(topicExchangeName);
    }

    /**
     * Binding binding.
     *
     * @param queue the queue
     * @param exchange the exchange
     * @return the binding
     */
    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }

    /**
     * Deadletter queue queue.
     *
     * @return the queue
     */
    @Bean
    Queue deadletter_queue() {
        return new Queue(deadletterQueueName, false);
    }

    /**
     * Deadletter exchange fanout exchange.
     *
     * @return the fanout exchange
     */
    @Bean
    FanoutExchange deadletter_exchange() {
        return new FanoutExchange(deadletterFanoutExchangeName);
    }

    /**
     * Deadletter binding binding.
     *
     * @param deadletter_queue the deadletter queue
     * @param deadletter_exchange the deadletter exchange
     * @return the binding
     */
    @Bean
    Binding deadletter_binding(Queue deadletter_queue, FanoutExchange deadletter_exchange) {
        return BindingBuilder.bind(deadletter_queue).to(deadletter_exchange);
    }

    /**
     * Json message converter message converter.
     *
     * @return the message converter
     */
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * Rabbit template amqp template.
     *
     * @param connectionFactory the connection factory
     * @return the amqp template
     */
    @Bean
    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {

        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
