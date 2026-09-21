package org.ercsn.marketplace.ticketing.infrastructure.event;

import org.ercsn.marketplace.common.infrastructure.event.dto.CustomerCreated;
import org.ercsn.marketplace.common.infrastructure.event.dto.EventUpdated;
import org.ercsn.marketplace.ticketing.application.CreateCustomerUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class TicketingEventListener {
    private static final Logger log = LoggerFactory.getLogger(TicketingEventListener.class);

    private final CreateCustomerUseCase createCustomerUseCase;

    public TicketingEventListener(CreateCustomerUseCase createCustomerUseCase) {
        this.createCustomerUseCase = createCustomerUseCase;
    }

    @EventListener
    @Async
    public void handle(CustomerCreated event) {
        log.info("Customer created {}", event);
        createCustomerUseCase.execute(event);
    }

    @EventListener
    @Async
    public void handle(EventUpdated event) {
        log.info("Event updated {}", event);
    }

}
