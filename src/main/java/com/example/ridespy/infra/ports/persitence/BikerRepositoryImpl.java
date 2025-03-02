package com.example.ridespy.infra.ports.persitence;

import com.example.ridespy.application.port.BikerRepository;
import com.example.ridespy.domain.Biker;
import com.example.ridespy.domain.vo.BikerId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BikerRepositoryImpl implements BikerRepository {

    private final BikerMongoRepository repository;

    @Override
    public Optional<Biker> findById(BikerId bikerId) {
        var documentOpt = repository.findById(bikerId.id());
        if (documentOpt.isEmpty()) {
            return Optional.empty();
        }

        var document = documentOpt.get();
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
