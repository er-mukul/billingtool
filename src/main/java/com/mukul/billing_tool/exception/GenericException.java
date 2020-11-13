package com.mukul.billing_tool.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class GenericException extends RuntimeException {
    private String message;
    private HttpStatus httpStatus;

    protected GenericException() {
        super(new RuntimeException());
    }
}
