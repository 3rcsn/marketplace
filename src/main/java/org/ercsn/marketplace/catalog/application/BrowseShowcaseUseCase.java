package org.ercsn.marketplace.catalog.application;

import org.ercsn.marketplace.catalog.application.dto.EventOutput;
import org.ercsn.marketplace.catalog.domain.Event;
import org.ercsn.marketplace.catalog.domain.EventMetadataRepository;
import org.ercsn.marketplace.catalog.domain.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class BrowseShowcaseUseCase {
    private static final Logger log = LoggerFactory.getLogger(BrowseShowcaseUseCase.class);
    private final EventRepository eventRepository;
    private final EventEnricher eventEnricher;

    public BrowseShowcaseUseCase(EventRepository eventRepository, EventMetadataRepository eventMetadataRepository, EventEnricher eventEnricher) {
        this.eventRepository = eventRepository;
        this.eventEnricher = eventEnricher;
    }

    public List<EventOutput> execute() {
        var futures = eventRepository.findAll().stream().map(eventEnricher::enrich).toList();

        var events = futures.stream()
                .map(CompletableFuture::join)
                .map(EventOutput::from)
                .toList();

        log.info("Events enriched {}", events);

        return events;
    }
}
