package com.core.customerservice.person.domain.dtos;

import com.core.customerservice.person.domain.Gender;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class PersonPutDTO {
    @NotNull
    private String name;
    @NotNull
    private Gender gender;
    @NotNull
    private LocalDate dateOfBirth;
    @NotNull
    private String identification;
    @NotNull
    private String address;
    @NotNull
    private String phone;
}
