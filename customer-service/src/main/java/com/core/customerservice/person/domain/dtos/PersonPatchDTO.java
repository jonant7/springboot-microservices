package com.core.customerservice.person.domain.dtos;

import com.core.customerservice.person.domain.Gender;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class PersonPatchDTO {
    private String name;
    private Gender gender;
    private LocalDate dateOfBirth;
    private String identification;
    private String address;
    private String phone;
}
