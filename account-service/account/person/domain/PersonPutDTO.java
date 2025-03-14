package com.core.customerservice.person.domain;

import lombok.Getter;

import java.util.Date;

@Getter
public class PersonPutDTO {
    private final String name;
    private final Gender gender;
    private final Date dateOfBirth;
    private final String identification;
    private final String address;
    private final String phone;

    public PersonPutDTO(Person person) {
        this.name = person.getName();
        this.gender = person.getGender();
        this.dateOfBirth = person.getDateOfBirth();
        this.identification = person.getIdentification();
        this.address = person.getAddress();
        this.phone = person.getPhone();
    }
}
