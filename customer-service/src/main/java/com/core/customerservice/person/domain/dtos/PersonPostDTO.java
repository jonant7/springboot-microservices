package com.core.customerservice.person.domain.dtos;

import com.core.customerservice.person.domain.Gender;
import com.core.customerservice.person.domain.Person;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
public class PersonPostDTO {
    @NotNull
    private String name;
    @NotNull
    private Gender gender;
    @NotNull
    private LocalDate dateOfBirth;
    @NotNull
    private String identification;
    private String address;
    private String phone;

    public PersonPostDTO(Person person) {
        this.name = person.getName();
        this.gender = person.getGender();
        this.dateOfBirth = person.getDateOfBirth();
        this.identification = person.getIdentification();
        this.address = person.getAddress();
        this.phone = person.getPhone();
    }

}
