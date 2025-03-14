package com.core.customerservice.customer.domain;

import com.core.customerservice.customer.domain.dtos.CustomerPatchDTO;
import com.core.customerservice.customer.domain.dtos.CustomerPutDTO;
import com.core.customerservice.person.domain.Gender;
import com.core.customerservice.person.domain.Person;
import com.core.customerservice.shared.domain.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTest {

    private Customer customer;

    @BeforeEach
    public void setUp() {
        Person person = Person.builder()
                .name("John Doe")
                .gender(Gender.MALE)
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .identification("123456789")
                .address("123 Elm St")
                .phone("+593987654321")
                .build();
        customer = Customer.builder()
                .password("password")
                .status(Status.ACTIVE)
                .person(person)
                .build();
    }

    @Test
    public void testUpdateMethod() {
        CustomerPutDTO dto = new CustomerPutDTO(
                "newPassword",
                Status.INACTIVE,
                "John Doe",
                Gender.MALE,
                LocalDate.of(1990, 1, 1),
                "123456789",
                "123 Elm St",
                "555-1234"
        );

        customer.update(dto);

        assertEquals("newPassword", customer.getPassword());
        assertEquals(Status.INACTIVE, customer.getStatus());
    }

    @Test
    public void testEditMethod() {
        CustomerPatchDTO dto = new CustomerPatchDTO(
                "newPassword", null, null, null, null,
                "987654321",
                "456 Elm St",
                "+593123456789"
        );

        customer.edit(dto);

        assertEquals("newPassword", customer.getPassword());
        assertEquals(Status.ACTIVE, customer.getStatus());
        assertEquals("123456789", customer.getPerson().getIdentification());
        assertEquals("123 Elm St", customer.getPerson().getAddress());
        assertEquals("+593987654321", customer.getPerson().getPhone());
    }
}