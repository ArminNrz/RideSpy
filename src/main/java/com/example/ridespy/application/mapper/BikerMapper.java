package com.example.ridespy.application.mapper;

import com.example.ridespy.domain.Biker;
import com.example.ridespy.infra.ports.api.dto.BikerNearByDto;
import org.springframework.stereotype.Component;

@Component
public class BikerMapper {
    public BikerNearByDto bikerNearByDto(Biker biker) {
        return new BikerNearByDto(
                biker.getId().id(),
                biker.getLocation().getLatitude(),
                biker.getLocation().getLongitude()
        );
    }
}
