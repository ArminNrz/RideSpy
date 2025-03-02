package com.example.ridespy.infra.configuration.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.SerializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.DeserializationException;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
@Slf4j
public class KafkaExceptionHandler {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Bean
    public DefaultErrorHandler kafkaErrorHandler() {
        FixedBackOff backOff = new FixedBackOff(0, 3);

        DeadLetterPublishingRecoverer recoverer = new DeadLetterPublishingRecoverer(kafkaTemplate, (record, exception) -> {
            //log.error("Failed to process record: {}, exception: {}", record, exception.getMessage());
            return new TopicPartition("BIKER_LOCATION_DLQ", record.partition());
        });

        DefaultErrorHandler errorHandler = new DefaultErrorHandler(recoverer, backOff);

        errorHandler.addNotRetryableExceptions(
                IllegalArgumentException.class,
                SerializationException.class,
                DeserializationException.class,
                JsonProcessingException.class
        );

        return errorHandler;
    }
}
