package org.ercsn.marketplace.ticketing.domain;

import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository {
    void save(Customer customer);
}
