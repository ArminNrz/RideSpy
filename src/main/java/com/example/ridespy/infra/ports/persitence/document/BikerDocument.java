package com.example.ridespy.infra.ports.persitence.document;

import com.example.ridespy.domain.BikerLocationEvent;
import com.example.ridespy.domain.vo.BikerAvailability;
import com.example.ridespy.domain.vo.BikerLocation;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
public class BikerDocument {
    private String id;
    private BikerAvailability availability;
    private BikerLocation location;
    private List<BikerLocationEvent> events = new ArrayList<>();

    public String getId() {
        return id;
    }

    public BikerAvailability getAvailability() {
        return availability;
    }

    public BikerLocation getLocation() {
        return location;
    }

    public List<BikerLocationEvent> getEvents() {
        return events;
    }
}
