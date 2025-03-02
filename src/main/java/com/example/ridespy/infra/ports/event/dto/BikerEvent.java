package com.example.ridespy.infra.ports.event.dto;

import com.example.ridespy.domain.vo.BikerAvailability;
import com.example.ridespy.domain.vo.EventType;

import java.io.Serializable;

public record BikerEvent(
        EventType eventType,
        String bikerId,
        BikerAvailability availability,
        Double lat,
        Double lon
) implements Serializable {
}
