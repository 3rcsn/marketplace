package org.ercsn.marketplace.ticketing.infrastructure.persistence.repository;

import org.ercsn.marketplace.ticketing.infrastructure.persistence.entity.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(exported = false)
public interface EventCrudRepository extends CrudRepository<Event, UUID> {
}
