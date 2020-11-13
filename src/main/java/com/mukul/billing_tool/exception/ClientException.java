package com.mukul.billing_tool.exception;

import org.springframework.http.HttpStatus;

import java.util.Optional;
import java.util.function.Supplier;

public class ClientException {
    private static final String message404 = "Resource not available. Kindly check the URL";
    private static final String messageDefault = "Unhandled Client Exception.";

    public static Supplier<ClientException> builder = ClientException::new;
    private String message;
    private HttpStatus httpStatus;

    public ClientException withMessage(final String message){
        this.message = message;
        return this;
    }

    public ClientException withStatus(final HttpStatus httpStatus){
        this.httpStatus = httpStatus;
        return this;
    }

    public GenericException build(){
        final GenericException targetEx = new GenericException();
        httpStatus = Optional.ofNullable(httpStatus).orElse(HttpStatus.BAD_REQUEST);
        targetEx.setMessage(Optional
                .ofNullable(message)
                .orElseGet(() -> {
                    String messageTemp = messageDefault;
                    if(httpStatus==HttpStatus.NOT_FOUND){
                        messageTemp = message404;
                    }
                    return messageTemp;
                }));
        targetEx.setHttpStatus(httpStatus);
        return targetEx;
    }
}
