package org.ercsn.marketplace.catalog.application;

import org.ercsn.marketplace.catalog.domain.Event;
import org.ercsn.marketplace.catalog.domain.EventMetadataRepository;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class EventEnricher {
    private static  final Logger log = LoggerFactory.getLogger(EventEnricher.class);

    private final EventMetadataRepository eventMetadataRepository;

    public EventEnricher(EventMetadataRepository eventMetadataRepository) {
        this.eventMetadataRepository = eventMetadataRepository;
    }

    @Async
    public CompletableFuture<Event> enrich(Event event) {
        log.info("Event {}", event);

        var metadata = eventMetadataRepository.findByEventId(event.getId());
        event.setMetadata(metadata);

        return CompletableFuture.completedFuture(event);
    }
}
