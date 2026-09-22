package org.ercsn.marketplace.ticketing.infrastructure.http.request;

import org.ercsn.marketplace.ticketing.domain.SeatId;

public record SeatSelectionRequest(String id) {
    public SeatId toInput() {
        return new SeatId(id);
    }
}
