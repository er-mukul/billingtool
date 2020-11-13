package com.mukul.billing_tool.dom.common;

import com.mukul.billing_tool.exception.ClientException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class BeanValidation {
    public BeanValidation() {

    }

    public <T extends DomainDTO> T dtoValidation(final T domainDTO) {
        domainDTO.getValidationErrors().ifPresent((values) -> {
            throw ((ClientException) ClientException.builder.get()).withStatus(HttpStatus.BAD_REQUEST).build();
        });
        return domainDTO;
    }
}
