package com.example.ridespy.infra.ports.api.dto;

import java.io.Serializable;

public record BikerNearByDto(
        String bikerId,
        double lat,
        double lon
) implements Serializable {
}
