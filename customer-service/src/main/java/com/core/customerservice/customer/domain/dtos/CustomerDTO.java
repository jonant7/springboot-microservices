package com.core.customerservice.customer.domain.dtos;

import com.core.customerservice.customer.domain.Customer;
import com.core.customerservice.shared.domain.AbstractEntityDTO;
import com.core.customerservice.shared.domain.Status;
import lombok.Getter;

@Getter
public class CustomerDTO extends AbstractEntityDTO {
    private final String name;
    private final String gender;
    private final Integer age;
    private final String identification;
    private final String address;
    private final String phone;
    private final String password;
    private final Status status;

    public CustomerDTO(Customer customer) {
        super(customer.getId());
        this.name = customer.getPerson().getName();
        this.gender = customer.getPerson().getGender().toString();
        this.age = customer.getPerson().getAge();
        this.identification = customer.getPerson().getIdentification();
        this.address = customer.getPerson().getAddress();
        this.phone = customer.getPerson().getPhone();
        this.password = customer.getPassword();
        this.status = customer.getStatus();
    }
}
