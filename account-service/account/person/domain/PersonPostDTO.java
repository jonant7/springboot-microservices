package com.core.customerservice.person.domain;

import com.core.customerservice.shared.domain.AbstractEntityDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
public class PersonPostDTO {
    private final String name;
    private final Gender gender;
    private final Date dateOfBirth;
    private final String identification;
    private final String address;
    private final String phone;

    public PersonPostDTO(Person person) {
        this.name = person.getName();
        this.gender = person.getGender();
        this.dateOfBirth = person.getDateOfBirth();
        this.identification = person.getIdentification();
        this.address = person.getAddress();
        this.phone = person.getPhone();
    }

}
