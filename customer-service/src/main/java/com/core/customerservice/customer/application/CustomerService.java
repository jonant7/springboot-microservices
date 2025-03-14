package com.core.customerservice.customer.application;

import com.core.customerservice.customer.domain.Customer;
import com.core.customerservice.customer.domain.dtos.CustomerPatchDTO;
import com.core.customerservice.customer.domain.dtos.CustomerPostDTO;
import com.core.customerservice.customer.domain.dtos.CustomerPutDTO;
import com.core.customerservice.customer.domain.exceptions.CustomerAlreadyExistsException;
import com.core.customerservice.customer.domain.exceptions.CustomerNotFoundException;
import com.core.customerservice.customer.infrastructure.CustomerRepository;
import com.core.customerservice.person.application.PersonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository repository;
    private final PersonService personService;
    private final CustomerEventPublisher eventPublisher;

    public CustomerService(CustomerRepository personRepository, PersonService personService, CustomerEventPublisher eventPublisher) {
        this.repository = personRepository;
        this.personService = personService;
        this.eventPublisher = eventPublisher;
    }

    public List<Customer> list() {
        return repository.findAll();
    }

    public Optional<Customer> get(Long id) {
        return repository.findById(id);
    }

    @Transactional(rollbackFor = Throwable.class)
    public Customer create(CustomerPostDTO dto) {
        final var person = personService.create(dto.getPersonPostDTO());
        repository.findByPerson(person)
                .ifPresent(p -> {
                    throw new CustomerAlreadyExistsException(dto.getIdentification());
                });
        Customer customer = Customer.builder()
                .password(dto.getPassword())
                .status(dto.getStatus())
                .person(person).build();

        final var savedCustomer = repository.save(customer);

        eventPublisher.publishCustomerCreated(savedCustomer.getId(), dto.getAccounts());

        return savedCustomer;
    }

    @Transactional(rollbackFor = Throwable.class)
    public Customer update(Long id, CustomerPutDTO dto) {
        return repository.findById(id)
                .map(customer -> {
                    customer.getPerson().update(dto.getPersonPutDTO());
                    customer.update(dto);
                    return repository.save(customer);
                })
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @Transactional(rollbackFor = Throwable.class)
    public Customer edit(Long id, CustomerPatchDTO dto) {
        return repository.findById(id)
                .map(customer -> {
                    customer.getPerson().edit(dto.getPersonPatchDTO());
                    customer.edit(dto);
                    return repository.save(customer);
                })
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @Transactional(rollbackFor = Throwable.class)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new CustomerNotFoundException(id);
        }
        repository.deleteById(id);
    }

}
