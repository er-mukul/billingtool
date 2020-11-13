package com.mukul.billing_tool.http.handlers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.test.StepVerifier;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

class ErrorHandlerTest {
    @InjectMocks
    private ErrorHandler errorHandler;

    private ServerRequest serverRequest;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
        serverRequest = mock(ServerRequest.class);
    }

    @Test
    void notFound() {
        StepVerifier.create(errorHandler.notFound(serverRequest))
                .assertNext(value -> {
                    assertThat("Status should be 404",value.statusCode().equals(HttpStatus.NOT_FOUND));
                })
                .expectComplete()
                .verify();
    }
}