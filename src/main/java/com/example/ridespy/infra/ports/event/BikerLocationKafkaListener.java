package com.example.ridespy.infra.ports.event;

import com.example.ridespy.application.BikerLocationApp;
import com.example.ridespy.infra.ports.event.dto.BikerEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(name = "app.biker-location-consumer-enabled", havingValue = "true", matchIfMissing = true)
public class BikerLocationKafkaListener {

    private final BikerLocationApp app;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "${spring.kafka.topic.biker-event}", groupId = "ride-spy")
    public void consume(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) throws JsonProcessingException {
        BikerEvent event = objectMapper.readValue(record.value(), BikerEvent.class);
        app.update(event);
        acknowledgment.acknowledge();
    }
}
