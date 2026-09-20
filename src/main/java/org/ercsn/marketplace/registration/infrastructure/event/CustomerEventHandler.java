package org.ercsn.marketplace.registration.infrastructure.event;

import org.ercsn.marketplace.catalog.common.infrastructure.event.dto.CustomerCreated;
import org.ercsn.marketplace.registration.domain.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class CustomerEventHandler {
    private static final Logger log = LoggerFactory.getLogger(CustomerEventHandler.class);

    private ApplicationEventPublisher publisher;

    public CustomerEventHandler(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @HandleAfterCreate
        public void handleAfterCreate(Customer customer){
        log.warn("CustomerEventHandler#handleAfterCreate");
        publisher.publishEvent(new CustomerCreated(customer.getId().toString(), customer.getName()));
    }

    @HandleAfterSave
    public void handleAfterSave(Customer customer){
        log.warn("CustomerEventHandler#handleAfterSave");
    }

    @HandleAfterDelete
    public void handleAfterDelete(Customer customer){
        log.warn("CustomerEventHandler#handleAfterDelete");
    }
}