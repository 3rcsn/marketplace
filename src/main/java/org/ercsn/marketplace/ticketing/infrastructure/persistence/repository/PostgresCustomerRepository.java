package org.ercsn.marketplace.ticketing.infrastructure.persistence.repository;

import org.ercsn.marketplace.ticketing.domain.Customer;
import org.ercsn.marketplace.ticketing.domain.CustomerRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PostgresCustomerRepository implements CustomerRepository {
    private final CustomerCrudRepository customerCrudRepository;

    public PostgresCustomerRepository(CustomerCrudRepository customerCrudRepository) {
        this.customerCrudRepository = customerCrudRepository;
    }

    @Override
    public void save(Customer customer) {
        var entity = new Customer()
    }
}
