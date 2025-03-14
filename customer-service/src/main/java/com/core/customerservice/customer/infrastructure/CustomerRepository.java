package com.core.customerservice.customer.infrastructure;

import com.core.customerservice.customer.domain.Customer;
import com.core.customerservice.person.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByPerson(Person person);
}
