package com.mukul.billing_tool.exception;

import org.springframework.http.HttpStatus;

import java.util.Optional;
import java.util.function.Supplier;

public class ClientExceptionMessaging {
    private static final String MESSAGE_404 = "Resource not available. Kindly check the URL";
    private static final String MESSAGE_DEFAULT = "Unhandled Client Exception.";

    public static Supplier<ClientExceptionMessaging> builder = ClientExceptionMessaging::new;
    private String message;
    private HttpStatus httpStatus;

    public ClientExceptionMessaging withMessage(final String message){
        this.message = message;
        return this;
    }

    public ClientExceptionMessaging withStatus(final HttpStatus httpStatus){
        this.httpStatus = httpStatus;
        return this;
    }

    public GenericException build(){
        final GenericException targetEx = new GenericException();
        httpStatus = Optional.ofNullable(httpStatus).orElse(HttpStatus.BAD_REQUEST);
        targetEx.setMessage(Optional
                .ofNullable(message)
                .orElseGet(() -> {
                    String messageTemp = MESSAGE_DEFAULT;
                    if(httpStatus==HttpStatus.NOT_FOUND){
                        messageTemp = MESSAGE_404;
                    }
                    return messageTemp;
                }));
        targetEx.setHttpStatus(httpStatus);
        return targetEx;
    }
}
