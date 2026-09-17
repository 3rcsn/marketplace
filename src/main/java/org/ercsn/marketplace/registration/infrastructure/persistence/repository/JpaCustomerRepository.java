package org.ercsn.marketplace.registration.infrastructure.persistence.repository;

import org.ercsn.marketplace.registration.domain.Customer;
import org.ercsn.marketplace.registration.domain.CustomerRepository;

import java.util.List;

public class JpaCustomerRepository implements CustomerRepository {
    @Override
    public Customer save(Customer customer) {
        return null;
    }

    @Override
    public List<Customer> findAll() {
        return List.of();
    }
}
