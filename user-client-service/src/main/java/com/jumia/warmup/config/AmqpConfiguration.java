package com.jumia.warmup.config;

import com.jumia.warmup.Listener.UserRegistrationListener;
import com.jumia.warmup.util.Constants;
import java.util.HashMap;
import java.util.Map;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type Amqp configuration.
 */
@Configuration
public class AmqpConfiguration {

    @Value(Constants.QUEUE_NAME)
    private String queueName;

    @Value(Constants.TOPIC_EXCHANGE_NAME)
    private String topicExchangeName;

    @Value(Constants.ROUTING_KEY)
    private String routingKey;

    @Value(Constants.DEAD_LETTER_QUEUE_NAME)
    private String deadletterQueueName;

    @Value(Constants.DEAD_LETTER_FANOUT_EXCHANGE_NAME)
    private String deadletterFanoutExchangeName;

    /**
     * Queue queue.
     *
     * @return the queue
     */
    @Bean
    Queue queue() {

        Map<String, Object> args = new HashMap<>();

        args.put(Constants.X_DEAD_LETTER_EXCHANGE, deadletterFanoutExchangeName);
        args.put(Constants.X_DEAD_LETTER_ROUTING_KEY, deadletterQueueName);

        return new Queue(queueName, true, false, false, args);
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
    Binding binding(final Queue queue, final TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }

    /**
     * Deadletter queue queue.
     *
     * @return the queue
     */
    @Bean
    Queue deadletter_queue() {
        return new Queue(deadletterQueueName);
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
    Binding deadletter_binding(final Queue deadletter_queue, final FanoutExchange deadletter_exchange) {
        return BindingBuilder.bind(deadletter_queue).to(deadletter_exchange);
    }

    /**
     * Connection factory connection factory.
     *
     * @return the connection factory
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory();
    }

    /**
     * Container simple message listener container.
     *
     * @param connectionFactory the connection factory
     * @param listenerAdapter the listener adapter
     * @return the simple message listener container
     */
    @Bean
    SimpleMessageListenerContainer container(
        final ConnectionFactory connectionFactory,
        final MessageListenerAdapter listenerAdapter) {

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);

        return container;
    }

    /**
     * Listener adapter message listener adapter.
     *
     * @param userRegistrationListener the user registration listener
     * @return the message listener adapter
     */
    @Bean
    MessageListenerAdapter listenerAdapter(final UserRegistrationListener userRegistrationListener) {

        return new MessageListenerAdapter(userRegistrationListener, Constants.DEFAULT_LISTENER_METHOD);
    }

    /**
     * Rabbit template rabbit template.
     *
     * @param connectionFactory the connection factory
     * @return the rabbit template
     */
    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {

        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    /**
     * Jackson 2 json message converter jackson 2 json message converter.
     *
     * @return the jackson 2 json message converter
     */
    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
