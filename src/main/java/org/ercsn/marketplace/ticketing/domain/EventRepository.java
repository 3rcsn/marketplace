package org.ercsn.marketplace.ticketing.domain;

import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository {
    void save(Event event);
    boolean existsSeat(EventId eventId, SeatId seatId);
    boolean tryLockSeat(EventId eventId, SeatId seatId, CustomerId customerId);
}
