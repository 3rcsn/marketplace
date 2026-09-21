package org.ercsn.marketplace.ticketing.domain;

import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository {
    void save(Event event);
}
