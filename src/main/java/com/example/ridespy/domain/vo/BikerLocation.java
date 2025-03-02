package com.example.ridespy.domain.vo;

import com.example.ridespy.domain.util.DateTimeUtils;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.io.Serializable;

@ToString
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

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public GeoJsonPoint getGeoJsonPoint() {
        return new GeoJsonPoint(longitude, latitude);
    }
}
