package com.example.ridespy.domain;

import com.example.ridespy.domain.vo.BikerAvailability;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BikerService {

    public Biker updateLocation(Optional<Biker> domainOptional, double lat, double lon) {
        Biker updatedDomain;
        if (domainOptional.isEmpty()) {
            updatedDomain = Biker.create(lat, lon);
        }
        else {
            updatedDomain = domainOptional.get().updateLocation(lat, lon);
        }

        return updatedDomain;
    }

    public void changeAvailability(Biker domain, BikerAvailability availability) {
        if (domain.getAvailability().equals(availability)) {
            return;
        }
        domain.updateAvailability(availability);
    }
}
