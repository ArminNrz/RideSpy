package com.example.ridespy.application;

import com.example.ridespy.application.mapper.BikerMapper;
import com.example.ridespy.application.port.BikerRepository;
import com.example.ridespy.domain.Biker;
import com.example.ridespy.domain.BikerService;
import com.example.ridespy.domain.vo.BikerId;
import com.example.ridespy.domain.vo.EventType;
import com.example.ridespy.infra.ports.api.dto.BikerNearByDto;
import com.example.ridespy.infra.ports.event.dto.BikerEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BikerLocationApp {

    private final BikerService bikerService;
    private final BikerRepository bikerRepository;
    private final BikerMapper mapper;

    public void update(BikerEvent event) {
        var bikerId = new BikerId(event.bikerId());
        Optional<Biker> domainOptional = bikerRepository.findById(bikerId);
        var domain = bikerService.updateLocation(domainOptional, event.bikerId(), event.lat(), event.lon());

        if (EventType.UPDATE.equals(event.type())) {
            bikerService.changeAvailability(domain, event.availability());
        }
        bikerRepository.save(domain);
    }

    public List<BikerNearByDto> findNearBy(double lat, double lon) {
        return bikerRepository.findNearByBikersInMeters(lat, lon, 2)
                .stream().map(mapper::bikerNearByDto).toList();
    }
}
