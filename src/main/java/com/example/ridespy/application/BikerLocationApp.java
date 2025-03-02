package com.example.ridespy.application;

import com.example.ridespy.domain.BikerService;
import com.example.ridespy.domain.vo.BikerId;
import com.example.ridespy.infra.ports.event.dto.BikerEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BikerLocationApp {

    private final BikerService bikerService;

    public void updateLocation(BikerEvent event) {
        var bikerId = new BikerId(event.bikerId());
        bikerService.updateLocation(bikerId, event.lat(), event.lon());
    }
}
