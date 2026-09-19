package org.ercsn.marketplace.catalog.infrastructure.event;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import org.ercsn.marketplace.catalog.infrastructure.persistence.entity.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventListener {
    private static final Logger log = LoggerFactory.getLogger(EventListener.class);

    @PostPersist
    public void onEventCreated(Event event) {
        log.info("Event created via @PostPersist {}", event);
    }

    @PostUpdate
    public void onEventUpdated(Event event) {
        log.info("Event updated via @PostUpdate {}", event);
    }

    @PostRemove
    public void onEventRemoved(Event event) {
        log.info("Event removed via @PostRemove {}", event);
    }
}
