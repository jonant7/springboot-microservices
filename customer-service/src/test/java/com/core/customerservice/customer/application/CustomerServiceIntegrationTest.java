package com.core.customerservice.customer.application;

import com.core.customerservice.customer.domain.Customer;
import com.core.customerservice.customer.domain.dtos.CustomerPatchDTO;
import com.core.customerservice.customer.domain.dtos.CustomerPostDTO;
import com.core.customerservice.customer.domain.dtos.CustomerPutDTO;
import com.core.customerservice.customer.domain.exceptions.CustomerNotFoundException;
import com.core.customerservice.customer.infrastructure.CustomerRepository;
import com.core.customerservice.person.domain.Gender;
import com.core.customerservice.shared.domain.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional
public class CustomerServiceIntegrationTest {

    @Autowired
    private CustomerService service;

    @Autowired
    private CustomerRepository repository;

    private CustomerPostDTO customerPostDTO;
    private CustomerPutDTO customerPutDTO;
    private CustomerPatchDTO customerPatchDTO;

    @BeforeEach
    public void setUp() {
        customerPostDTO = new CustomerPostDTO(
                "newPassword",
                Status.INACTIVE,
                "John Doe",
                Gender.MALE,
                LocalDate.of(1990, 1, 1),
                "123456789",
                "123 Elm St",
                "+593987654321",
                Collections.emptyList()
        );

        customerPutDTO = new CustomerPutDTO(
                "updatedPassword",
                Status.ACTIVE,
                "Jane Doe",
                Gender.FEMALE,
                LocalDate.of(1992, 2, 2),
                "987654321",
                "456 Oak St",
                "+593987654322"
        );

        customerPatchDTO = new CustomerPatchDTO(
                "newPassword123",
                Status.ACTIVE,
                "John Smith",
                Gender.MALE,
                LocalDate.of(1990, 1, 1),
                "123456789",
                "789 Pine St",
                "+593987654323"
        );
    }

    @Test
    public void testCreateCustomer() {
        Customer createdCustomer = service.create(customerPostDTO);
        assertNotNull(createdCustomer.getId());

        Optional<Customer> customerOptional = repository.findById(createdCustomer.getId());
        assertTrue(customerOptional.isPresent());
    }

    @Test
    public void testUpdateCustomer() {
        Customer createdCustomer = service.create(customerPostDTO);
        Long customerId = createdCustomer.getId();

        Customer updatedCustomer = service.update(customerId, customerPutDTO);
        assertNotNull(updatedCustomer);
        assertEquals("updatedPassword", updatedCustomer.getPassword());
        assertEquals(Status.ACTIVE, updatedCustomer.getStatus());
    }

    @Test
    public void testUpdateCustomerThrowsExceptionWhenCustomerNotFound() {
        assertThrows(CustomerNotFoundException.class, () -> service.update(999L, customerPutDTO));
    }

    @Test
    public void testEditCustomer() {
        Customer createdCustomer = service.create(customerPostDTO);
        Long customerId = createdCustomer.getId();
        Customer editedCustomer = service.edit(customerId, customerPatchDTO);
        assertNotNull(editedCustomer);
        assertEquals("newPassword123", editedCustomer.getPassword());
        assertEquals(Status.ACTIVE, editedCustomer.getStatus());
    }

    @Test
    public void testEditCustomerThrowsExceptionWhenCustomerNotFound() {
        assertThrows(CustomerNotFoundException.class, () -> service.edit(999L, customerPatchDTO));
    }

    @Test
    public void testDeleteCustomer() {
        Customer createdCustomer = service.create(customerPostDTO);
        Long customerId = createdCustomer.getId();

        service.delete(customerId);

        Optional<Customer> deletedCustomer = repository.findById(customerId);
        assertFalse(deletedCustomer.isPresent());
    }

    @Test
    public void testDeleteCustomerThrowsExceptionWhenCustomerNotFound() {
        assertThrows(CustomerNotFoundException.class, () -> service.delete(999L));
    }

    @Test
    public void testListCustomers() {
        service.create(customerPostDTO);
        assertFalse(service.list().isEmpty());
    }

}
