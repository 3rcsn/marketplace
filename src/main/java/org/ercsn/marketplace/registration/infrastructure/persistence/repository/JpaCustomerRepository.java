package org.ercsn.marketplace.registration.infrastructure.persistence.repository;

import org.ercsn.marketplace.registration.domain.Customer;
import org.ercsn.marketplace.registration.domain.CustomerId;
import org.ercsn.marketplace.registration.domain.CustomerRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Repository
public class JpaCustomerRepository implements CustomerRepository {
    private final CustomerEntityRepository customerEntityRepository;

    public JpaCustomerRepository(CustomerEntityRepository customerEntityRepository) {
        this.customerEntityRepository = customerEntityRepository;
    }

    @Override
    public Customer save(Customer customer) {
        var entity = mapper(customer);
        customerEntityRepository.save(entity);
        return customer;
    }

    @Override
    public List<Customer> findAll() {
        var iterable = customerEntityRepository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(JpaCustomerRepository::mapper)
                .toList();
    }

    private static org.ercsn.marketplace.registration.infrastructure.persistence.entity.Customer mapper(Customer customer) {
        var entity = new org.ercsn.marketplace.registration.infrastructure.persistence.entity.Customer();

        entity.setId(customer.getId().id());
        entity.setFistName(customer.getName());
        entity.setEmail(customer.getEmail());

        return entity;
    }

    private static Customer mapper(org.ercsn.marketplace.registration.infrastructure.persistence.entity.Customer entity) {
        String fullName = Optional.ofNullable(entity.getLastName())
                .map(lastName -> entity.getFistName() + " " + lastName )
                .orElseGet(entity::getFistName);

        return new Customer(new CustomerId(entity.getId()), fullName, entity.getEmail());
    }
}
