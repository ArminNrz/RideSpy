package com.example.ridespy.infra.ports.event;

import com.example.ridespy.application.BikerLocationApp;
import com.example.ridespy.infra.ports.event.dto.BikerEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "app.biker-location-consumer-enabled", havingValue = "true", matchIfMissing = true)
@RequiredArgsConstructor
@Slf4j
public class BikerLocationKafkaListener {

    private final BikerLocationApp app;

    @KafkaListener(topics = "${spring.kafka.topic.biker-event}", groupId = "ride-spy")
    public void consume(ConsumerRecord<String, BikerEvent> record, Acknowledgment acknowledgment) {
        log.debug("Received message [biker event], with key: {}, value: {} ", record.key(), record.value());
        app.updateLocation(record.value());
        acknowledgment.acknowledge();
    }
}
