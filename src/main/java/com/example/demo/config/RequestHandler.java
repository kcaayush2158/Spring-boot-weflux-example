package com.example.demo.config;

import com.example.demo.dto.Response;
import com.example.demo.exception.InputValidationException;
import com.example.demo.service.ReactiveMathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
public class RequestHandler {

    @Autowired
    private ReactiveMathService reactiveMathService;

    public Mono<ServerResponse> squareHandlerWithValidation(ServerRequest serverRequest) {
    int input = Integer.parseInt(serverRequest.pathVariable("input"));
    if(input<10 || input>20){
        return Mono.error(new InputValidationException(input));
    }
    Mono<Response> responseMono = this.reactiveMathService.findSquare(input);
    return ServerResponse.ok().body(responseMono,Response.class);
    }


}
