package com.dh.catalog.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "backendExchange";
    public static final String TOPIC_MOVIE_CREADA = "com.dh.backend.moviecreada";
    public static final String TOPIC_SERIE_CREADA = "com.dh.backend.seriecreada";

    public static final String QUEUE_MOVIE_CREADA ="queueMovieCreada";
    public static final String QUEUE_SERIE_CREADA ="queueSerieCreada";

    @Bean
    public TopicExchange appExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue queueMovieCreada(){
        return new Queue(QUEUE_MOVIE_CREADA);
    }
    @Bean
    public Queue queueSerieCreada(){
        return new Queue(QUEUE_SERIE_CREADA);
    }

    @Bean
    public Binding declareBindingSpecificMovie(){
        return BindingBuilder.bind(queueMovieCreada()).to(appExchange()).with(TOPIC_MOVIE_CREADA);
    }
    @Bean
    public Binding declareBindingSpecificSerie(){
        return BindingBuilder.bind(queueSerieCreada()).to(appExchange()).with(TOPIC_SERIE_CREADA);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }
    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}