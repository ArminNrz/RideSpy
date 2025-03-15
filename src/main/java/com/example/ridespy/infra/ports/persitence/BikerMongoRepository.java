package com.example.ridespy.infra.ports.persitence;

import com.example.ridespy.infra.ports.persitence.document.BikerDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BikerMongoRepository extends MongoRepository<BikerDocument, String> {
    @Query("{ 'bikerId' :  ?0 }")
    Optional<BikerDocument> findByBikerId(String bikerId);

    @Query("{ 'availability': 'ONLINE', 'point' : { $near : { $geometry : { type : 'Point', coordinates : [?1, ?0] }, $maxDistance : ?2 } } }")
    List<BikerDocument> findNearBy(double lat, double lon, double maxDistance);
}
