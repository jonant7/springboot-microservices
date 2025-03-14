package com.core.customerservice.person.domain.dtos;

import com.core.customerservice.person.domain.Gender;
import com.core.customerservice.person.domain.Person;
import com.core.customerservice.shared.domain.AbstractEntityDTO;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class PersonDTO extends AbstractEntityDTO {
    private final String name;
    private final Gender gender;
    private final LocalDate dateOfBirth;
    private final String identification;
    private final String address;
    private final String phone;

    public PersonDTO(Person person) {
        super(person.getId());
        this.name = person.getName();
        this.gender = person.getGender();
        this.dateOfBirth = person.getDateOfBirth();
        this.identification = person.getIdentification();
        this.address = person.getAddress();
        this.phone = person.getPhone();
    }
}
