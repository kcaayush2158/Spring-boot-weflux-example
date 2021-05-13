package com.example.demo.config;

import com.example.demo.dto.InputFailedValidationResponse;
import com.example.demo.exception.InputValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@Configuration
public class RouterConfig {
    @Autowired
    private RequestHandler requestHandler;


    @Bean
    public  RouterFunction<ServerResponse>  highLevelRouter(){
        return RouterFunctions.route()
               .path("/router",this::routerFunction)
                .build();
    }

    @Bean
   public  RouterFunction<ServerResponse>  routerFunction(){
        return RouterFunctions.route()
                .GET("/square/{input}/validation", RequestPredicates.path("*/1?").or(RequestPredicates.path("*/20")),requestHandler::squareHandlerWithValidation)
                .GET("/square/{input}/validation",request -> ServerResponse.badRequest().bodyValue("only 10-19 allowed"))
                .onError(InputValidationException.class,exceptionHandler())
                .build();
    }
    private BiFunction<Throwable, ServerRequest, Mono<ServerResponse>> exceptionHandler(){
        return (err , req)->{

            InputValidationException ex = (InputValidationException) err;
            InputFailedValidationResponse response = new InputFailedValidationResponse();
            response.setInput(ex.getInput());
            response.setMessage(ex.getMessage());
            response.setErrorCode(ex.getErrorCode());
            return ServerResponse.badRequest().bodyValue(response);

        };


    }




}
