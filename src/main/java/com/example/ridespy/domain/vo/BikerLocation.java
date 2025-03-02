package com.example.ridespy.domain.vo;

import com.example.ridespy.domain.util.DateTimeUtils;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Getter
@EqualsAndHashCode
public class BikerLocation implements Serializable {
    private final double latitude;
    private final double longitude;
    private final long timestamp;

    public BikerLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = DateTimeUtils.currentTimeMillis();
    }

    public BikerLocation(double latitude, double longitude, long timestamp) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = timestamp;
    }
}
