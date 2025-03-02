package com.example.ridespy.domain;

import com.example.ridespy.application.port.BikerRepository;
import com.example.ridespy.domain.exception.BikerNotFoundException;
import com.example.ridespy.domain.vo.BikerAvailability;
import com.example.ridespy.domain.vo.BikerId;
import org.springframework.stereotype.Service;

@Service
public class BikerService {

    private final BikerRepository repository;

    public BikerService(BikerRepository repository) {
        this.repository = repository;
    }

    public void updateLocation(BikerId bikerId, double lat, double lon) {
        var domainOptional = repository.findById(bikerId);
        Biker updatedDomain;
        if (domainOptional.isEmpty()) {
            updatedDomain = Biker.create(lat, lon);
        }
        else {
            updatedDomain = domainOptional.get().updateLocation(lat, lon);
        }
        repository.save(updatedDomain);
    }

    public void changeAvailability(BikerId bikerId, BikerAvailability availability) {
        Biker domain = repository.findById(bikerId).orElseThrow(BikerNotFoundException::new);
        domain.updateAvailability(availability);
        repository.save(domain);
    }

    public void makeBikerBanned(BikerId bikerId) {
        Biker domain = repository.findById(bikerId).orElseThrow(BikerNotFoundException::new);
        domain.updateAvailability(BikerAvailability.BANNED);
        repository.save(domain);
    }
}
