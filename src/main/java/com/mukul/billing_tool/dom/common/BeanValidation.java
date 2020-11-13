package com.mukul.billing_tool.dom.common;

import com.mukul.billing_tool.exception.ClientExceptionMessaging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class BeanValidation {
    public <T extends DomainDTO> T dtoValidation(final T domainDTO) {
        log.info("Validating the DTO");
        domainDTO.getValidationErrors().ifPresent(values -> {
            log.error("Error in DTO");
            throw ClientExceptionMessaging.builder.get().withStatus(HttpStatus.BAD_REQUEST).build();
        });
        return domainDTO;
    }
}
