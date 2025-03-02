package com.example.ridespy.domain.vo;

import io.micrometer.common.util.StringUtils;

import java.io.Serializable;

public record BikerId(
        String id
) implements Serializable {
    public BikerId {
        if (id == null || StringUtils.isEmpty(id)) {
            throw new IllegalArgumentException("Biker is null !");
        }
    }
}
