package com.example.ridespy.infra.ports.persitence.document;

import com.example.ridespy.domain.BikerLocationEvent;
import com.example.ridespy.domain.vo.BikerAvailability;
import lombok.Data;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
public class BikerDocument {
    private String id;
    private BikerAvailability availability;
    private List<BikerLocationEvent> events = new ArrayList<>();
    private long timestamp;

    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private GeoJsonPoint point;

    public String getId() {
        return id;
    }

    public BikerAvailability getAvailability() {
        return availability;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public List<BikerLocationEvent> getEvents() {
        return events;
    }

    public GeoJsonPoint getPoint() {
        return point;
    }
}
