package com.example.ridespy.infra.ports.persitence;

import com.example.ridespy.application.port.BikerRepository;
import com.example.ridespy.domain.Biker;
import com.example.ridespy.domain.vo.BikerId;
import com.example.ridespy.domain.vo.BikerLocation;
import com.example.ridespy.infra.ports.persitence.document.BikerDocument;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BikerRepositoryImpl implements BikerRepository {

    private final BikerMongoRepository repository;

    public BikerRepositoryImpl(BikerMongoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Biker> findById(BikerId bikerId) {
        var documentOpt = repository.findByBikerId(bikerId.id());
        if (documentOpt.isEmpty()) {
            return Optional.empty();
        }

        BikerDocument document = documentOpt.get();
        var domain = Biker.of(
                document.getBikerId(),
                new BikerLocation(document.getPoint().getY(), document.getPoint().getX(), document.getTimestamp()),
                document.getAvailability(),
                document.getEvents()
        );
        return Optional.of(domain);
    }

    @Override
    public void save(Biker biker) {
        BikerDocument document = new BikerDocument();
        document.setId(biker.getId().id());
        document.setBikerId(biker.getId().id());
        document.setAvailability(biker.getAvailability());
        document.setEvents(biker.getEventsAsList());
        document.setTimestamp(biker.getLocation().getTimestamp());
        document.setPoint(biker.getLocation().getGeoJsonPoint());

        repository.save(document);
    }

    @Override
    public List<Biker> findNearByBikersInMeters(double lat, double lon, long maxDistance) {
        return repository.findNearBy(lat, lon, maxDistance).stream()
                .map(d -> Biker.of(
                        d.getBikerId(),
                        new BikerLocation(d.getPoint().getY(), d.getPoint().getX(), d.getTimestamp()),
                        d.getAvailability(),
                        d.getEvents()
                )).toList();
    }
}
