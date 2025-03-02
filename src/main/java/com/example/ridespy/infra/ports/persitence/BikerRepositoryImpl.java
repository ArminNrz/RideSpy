package com.example.ridespy.infra.ports.persitence;

import com.example.ridespy.application.port.BikerRepository;
import com.example.ridespy.domain.Biker;
import com.example.ridespy.domain.vo.BikerId;
import com.example.ridespy.infra.ports.persitence.document.BikerDocument;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BikerRepositoryImpl implements BikerRepository {

    private final BikerMongoRepository repository;

    public BikerRepositoryImpl(BikerMongoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Biker> findById(BikerId bikerId) {
        var documentOpt = repository.findById(bikerId.id());
        if (documentOpt.isEmpty()) {
            return Optional.empty();
        }

        BikerDocument document = documentOpt.get();
        var domain = Biker.of(
                document.getId(),
                document.getLocation(),
                document.getAvailability(),
                document.getEvents()
        );
        return Optional.of(domain);
    }

    @Override
    public void save(Biker biker) {

    }
}
