package com.core.customerservice.shared.domain;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, Boolean> {

    @Override
    public Boolean convertToDatabaseColumn(Status status) {
        return (Objects.nonNull(status)) ? status.getValue() : null;
    }

    @Override
    public Status convertToEntityAttribute(Boolean dbValue) {
        return (Objects.nonNull(dbValue)) ? Status.fromBoolean(dbValue) : null;
    }
}