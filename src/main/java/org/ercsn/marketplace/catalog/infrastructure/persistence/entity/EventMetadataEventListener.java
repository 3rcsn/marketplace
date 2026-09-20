package org.ercsn.marketplace.catalog.infrastructure.persistence.entity;

import org.ercsn.marketplace.catalog.common.infrastructure.event.dto.EventUpdated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.stereotype.Component;


@Component
public class EventMetadataEventListener extends AbstractMongoEventListener<EventMetadata> {
    private static final Logger log = LoggerFactory.getLogger(EventMetadataEventListener.class);

    private final ApplicationEventPublisher publisher;

    public EventMetadataEventListener(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void onAfterSave(AfterSaveEvent<EventMetadata> event) {
        log.info("Event metadata save via onAfterSave {}", event.getDocument());
        this.publisher.publishEvent(EventUpdated.from(event.getSource()));
    }

    @Override
    public void onAfterDelete(AfterDeleteEvent<EventMetadata> event) {
        log.info("Event metadata delete via onAfterDelete {}", event.getDocument());
    }
}
