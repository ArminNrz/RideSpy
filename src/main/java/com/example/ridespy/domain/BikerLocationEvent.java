package com.example.ridespy.domain;

import com.example.ridespy.domain.util.DateTimeUtils;
import com.example.ridespy.domain.util.IdGenerator;
import com.example.ridespy.domain.vo.BikerAvailability;
import com.example.ridespy.domain.vo.EventType;
import lombok.Data;

import java.io.Serializable;

@Data
public class BikerLocationEvent implements Serializable {
    private String eventId;
    private String bikerId;
    private EventType eventType;
    private double latitude;
    private double longitude;
    private long locateTime;
    private BikerAvailability availability;
    private long timestamp;
    private boolean sent;

    public BikerLocationEvent(Biker biker, EventType eventType) {
        this.eventId = IdGenerator.generateId();
        this.bikerId = biker.getId().id();
        this.eventType = eventType;
        this.latitude = biker.getLocation().getLatitude();
        this.longitude = biker.getLocation().getLongitude();
        this.locateTime = biker.getLocation().getTimestamp();
        this.availability = biker.getAvailability();
        this.timestamp = DateTimeUtils.currentTimeMillis();
        this.sent = false;
    }
}
