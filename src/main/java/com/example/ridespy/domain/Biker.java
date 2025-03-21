package com.example.ridespy.domain;

import com.example.ridespy.domain.util.IdGenerator;
import com.example.ridespy.domain.vo.BikerAvailability;
import com.example.ridespy.domain.vo.BikerId;
import com.example.ridespy.domain.vo.BikerLocation;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static com.example.ridespy.domain.vo.BikerAvailability.ONLINE;
import static com.example.ridespy.domain.vo.EventType.CREATE;
import static com.example.ridespy.domain.vo.EventType.UPDATE;

@ToString
public class Biker implements Serializable {
    @Getter
    private final BikerId id;
    @Getter
    private BikerLocation location;
    @Getter
    private BikerAvailability availability;
    private final Queue<BikerLocationEvent> eventQueue;

    private Biker(String id, double lat, double lon) {
        this.id = new BikerId(id);
        this.location = new BikerLocation(lat, lon);
        this.availability = ONLINE;
        this.eventQueue = new LinkedList<>();
    }

    private Biker(BikerId id, BikerLocation location, BikerAvailability availability) {
        this.id = id;
        this.location = location;
        this.availability = availability;
        this.eventQueue = new LinkedList<>();
    }

    public static Biker create(String bikerId, double lat, double lon) {
        var biker = new Biker(bikerId, lat, lon);
        biker.eventQueue.offer(new BikerLocationEvent(biker, CREATE));
        return biker;
    }

    public static Biker of(String id, BikerLocation location, BikerAvailability availability, List<BikerLocationEvent> eventList) {
        var biker = new Biker(
                new BikerId(id),
                location,
                availability
        );
        biker.eventQueue.addAll(eventList);
        return biker;
    }

    public Biker updateLocation(double lat, double lon) {
        BikerLocation newLocation = new BikerLocation(lat, lon);
        if (this.location.equals(newLocation)) {
            return this;
        }
        this.location = newLocation;
        this.eventQueue.offer(new BikerLocationEvent(this, UPDATE));
        return this;
    }

    public void updateAvailability(BikerAvailability availability) {
        this.availability = availability;
        this.eventQueue.offer(new BikerLocationEvent(this, UPDATE));
    }

    public List<BikerLocationEvent> getEventsAsList() {
        return new LinkedList<>(this.eventQueue);
    }
}
