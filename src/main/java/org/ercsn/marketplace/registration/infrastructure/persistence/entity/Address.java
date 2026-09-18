package org.ercsn.marketplace.registration.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@RequiredArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String street;
    private String city;
    private String state;
    private String zipCode;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private Instant createdAt;

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s", street, city, state, zipCode);
    }
}
