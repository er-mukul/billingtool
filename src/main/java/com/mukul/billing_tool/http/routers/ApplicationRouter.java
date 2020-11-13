package com.mukul.billing_tool.http.routers;

import com.mukul.billing_tool.http.handlers.ErrorHandler;
import com.mukul.billing_tool.http.handlers.Version1Handler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@EnableWebFlux
public class ApplicationRouter {
    @Bean
    RouterFunction<?> mainRouterFunction(
            final Version1Router version1Router,
            final Version1Handler version1Handler,
            final ErrorHandler errorHandler
    ){
        return version1Router.doRoute(version1Handler,errorHandler)
                .andOther(route(RequestPredicates.all(),errorHandler::notFound));
    }

}
