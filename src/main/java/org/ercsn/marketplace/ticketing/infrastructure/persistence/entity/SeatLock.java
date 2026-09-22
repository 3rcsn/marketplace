package org.ercsn.marketplace.ticketing.infrastructure.persistence.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.index.Indexed;
import org.springframework.data.redis.core.RedisHash;

import java.time.Instant;

@RedisHash(value = "seat-locks", timeToLive = 30)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatLock {
    @Id
    private String id;

    @Indexed
    private String customerId;

    private Instant createdAt;
}
