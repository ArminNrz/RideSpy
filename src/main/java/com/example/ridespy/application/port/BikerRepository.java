package com.example.ridespy.application.port;

import com.example.ridespy.domain.Biker;
import com.example.ridespy.domain.vo.BikerId;

import java.util.Optional;

public interface BikerRepository {
    Optional<Biker> findById(BikerId bikerId);
    void save(Biker biker);
}
