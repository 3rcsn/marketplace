package org.ercsn.marketplace.ticketing.infrastructure.persistence.repository;

import org.ercsn.marketplace.ticketing.domain.Event;
import org.ercsn.marketplace.ticketing.domain.EventRepository;
import org.ercsn.marketplace.ticketing.domain.Seat;
import org.ercsn.marketplace.ticketing.domain.Sector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostgresEventRepository implements EventRepository {
    private final EventCrudRepository eventCrudRepository;
    private final Logger log = LoggerFactory.getLogger(PostgresEventRepository.class);

    public PostgresEventRepository(EventCrudRepository eventCrudRepository) {
        this.eventCrudRepository = eventCrudRepository;
    }

    @Override
    public void save(Event event) {
        log.info("Saving event {}", event);

        var sectors = event.getSeats().entrySet().stream()
            .map(entry -> {
                Sector domainSector = entry.getKey();
                List<Seat> domainSeats = entry.getValue();

                var seats = domainSeats.stream()
                        .map(s -> new org.ercsn.marketplace.ticketing.infrastructure.persistence.entity.Seat(
                                s.getId(),
                                s.getCorrelationId().id()
                        )).toList();

                return new org.ercsn.marketplace.ticketing.infrastructure.persistence.entity.Sector(
                      domainSector.getId(),
                      domainSector.getCorrelationId().id(),
                      domainSector.getPrice(),
                      seats
                );
            }).toList();

        var entity = new org.ercsn.marketplace.ticketing.infrastructure.persistence.entity.Event(
                event.getId(),
                event.getCorrelationId().id(),
                sectors
        );
        eventCrudRepository.save(entity);
    }
}
