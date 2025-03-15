package com.example.ridespy.infra.ports.persitence.document;

import com.example.ridespy.domain.BikerLocationEvent;
import com.example.ridespy.domain.vo.BikerAvailability;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BikerDocument {
    @Id
    private String id;
    private String bikerId;
    private BikerAvailability availability;
    private List<BikerLocationEvent> events = new ArrayList<>();
    private long timestamp;

    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private GeoJsonPoint point;
}
