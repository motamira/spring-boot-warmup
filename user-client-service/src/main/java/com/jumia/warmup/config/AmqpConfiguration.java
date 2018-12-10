package com.jumia.warmup.config;

import com.jumia.warmup.Listener.UserRegistrationListener;
import java.util.HashMap;
import java.util.Map;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.validation.SmartValidator;

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

    @Autowired
    private SmartValidator smartValidator;

    /**
     * Queue queue.
     *
     * @return the queue
     */
    @Bean
    Queue queue() {

        Map<String, Object> args = new HashMap<>();

        args.put("x-dead-letter-exchange", deadletterFanoutExchangeName);
        args.put("x-dead-letter-routing-key", deadletterQueueName);

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

        return new MessageListenerAdapter(userRegistrationListener, "onMessage");
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
