package com.projarq.broker;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.UUID;

@Configuration
public class RabbitMQConfig {
    private static final String EXCHANGE_NAME = "subscription.update.exchange";
    private static final String QUEUE_NAME = "subscription.update.queue.save.signature." + UUID.randomUUID();

    @Bean
    public Queue createQueue()
    {
        return new Queue(QUEUE_NAME);
    }

    @Bean
    public FanoutExchange createExchange()
    {
        return new FanoutExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding bindQueue(Queue subscriptionQueue, FanoutExchange fanoutExchange)
    {
        return BindingBuilder.bind(subscriptionQueue).to(fanoutExchange);
    }

    @Bean
    public MessageConverter jsonMessageConverter()
    {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate configureRabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter)
    {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

}
