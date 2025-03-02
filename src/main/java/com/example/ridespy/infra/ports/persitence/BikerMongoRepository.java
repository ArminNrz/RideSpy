package com.example.ridespy.infra.ports.persitence;

import com.example.ridespy.infra.ports.persitence.document.BikerDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BikerMongoRepository extends MongoRepository<BikerDocument, String> {
}
