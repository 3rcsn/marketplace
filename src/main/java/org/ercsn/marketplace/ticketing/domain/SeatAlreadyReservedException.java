package org.ercsn.marketplace.ticketing.domain;

public class SeatAlreadyReservedException extends RuntimeException {
    public SeatAlreadyReservedException() {
        super("Seat already reserved");
    }
}
