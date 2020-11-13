package com.mukul.billing_tool.http.handlers;

import com.mukul.billing_tool.exception.ClientException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Component
@RequiredArgsConstructor
public class ErrorHandler {
    public Mono<ServerResponse> notFound(final ServerRequest serverRequest){
        return Mono.just(ClientException.builder.get().withStatus(HttpStatus.NOT_FOUND).build()).transform(this::getResponse);
    }

    private <T extends Throwable> Mono<ServerResponse> getResponse(final Mono<T> monoError){
        return monoError.flatMap(error -> ServerResponse.status(HttpStatus.NOT_FOUND).body(fromValue(error)));
    }
}
