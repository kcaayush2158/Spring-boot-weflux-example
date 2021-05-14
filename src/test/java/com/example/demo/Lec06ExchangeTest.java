package com.example.demo;

import com.example.demo.dto.InputFailedValidationResponse;
import com.example.demo.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lec06ExchangeTest  extends DemoApplicationTests{


    @Autowired
    private WebClient webClient;

    @Test
    public void fluxTest() {
        Mono<Object> monoResponse = webClient.get()
                .uri("reactive-math/square/{input}/throw", 11)
                .exchangeToMono(this::exchange)
                .doOnNext(System.out::println)
                .doOnError(err->System.out.println(err.getMessage()));

        StepVerifier.create(monoResponse)
                .expectNextCount(1)
              .verifyComplete();


    }

    private  Mono<Object> exchange(ClientResponse response){
        if(response.rawStatusCode() == 400){
            return response.bodyToMono(InputFailedValidationResponse.class);
        }else {
            return response.bodyToMono(Response.class);
        }
    }
}
