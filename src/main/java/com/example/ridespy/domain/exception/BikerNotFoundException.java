package com.example.ridespy.domain.exception;

public class BikerNotFoundException extends RuntimeException {
    public BikerNotFoundException() {
        super("Biker not found !");
    }
}
