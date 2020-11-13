package com.mukul.billing_tool.dom.common;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface DomainDTO {
    @JsonIgnore
    default Optional<List<String>> getValidationErrors() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return Optional.of(validator.validate(this, new Class[0])).filter((set) -> {
            return set.size() > 0;
        }).map((set) -> {
            return (List)set.stream().map((violation) -> {
                return violation.getPropertyPath().toString() + " " + violation.getMessage();
            }).collect(Collectors.toList());
        });
    }
}
