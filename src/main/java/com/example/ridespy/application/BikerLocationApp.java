package com.example.ridespy.application;

import com.example.ridespy.application.port.BikerRepository;
import com.example.ridespy.domain.Biker;
import com.example.ridespy.domain.BikerService;
import com.example.ridespy.domain.vo.BikerId;
import com.example.ridespy.domain.vo.EventType;
import com.example.ridespy.infra.ports.event.dto.BikerEvent;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BikerLocationApp {

    private final BikerService bikerService;
    private final BikerRepository bikerRepository;

    public BikerLocationApp(BikerService bikerService, BikerRepository bikerRepository) {
        this.bikerService = bikerService;
        this.bikerRepository = bikerRepository;
    }

    public void updateLocation(BikerEvent event) {
        var bikerId = new BikerId(event.bikerId());
        Optional<Biker> domainOptional = bikerRepository.findById(bikerId);
        var domain = bikerService.updateLocation(domainOptional, event.lat(), event.lon());

        if (EventType.UPDATE.equals(event.type())) {
            bikerService.changeAvailability(domain, event.availability());
        }
        bikerRepository.save(domain);
    }
}
