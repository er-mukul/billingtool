package com.mukul.billing_tool.http.routers;

import com.mukul.billing_tool.http.handlers.ErrorHandler;
import com.mukul.billing_tool.http.handlers.Version1Handler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
public class Version1Router {
    public RouterFunction<ServerResponse> doRoute(final Version1Handler version1Handler, final ErrorHandler errorHandler){
        final String ADD_CUSTOMER_PATH = "/addcustomer";
        final String BILL_PATH = "/generatebill";
        final String VERSION_PATH = "/v1";

        final RouterFunction<ServerResponse> addCustomer =
                route(POST(ADD_CUSTOMER_PATH)
                        .and(accept(MediaType.APPLICATION_STREAM_JSON))
                        .and(contentType(MediaType.APPLICATION_STREAM_JSON)), version1Handler::saveCustomer);

        final RouterFunction<ServerResponse> generateBill =
                route(POST(BILL_PATH)
                      .and(accept(MediaType.APPLICATION_STREAM_JSON))
                        .and(contentType(MediaType.APPLICATION_STREAM_JSON)), version1Handler::generateBill);

        return route()
                .path(VERSION_PATH,() -> addCustomer.and(generateBill).andRoute(RequestPredicates.all(),errorHandler::notFound))
                .build();

    }
}
