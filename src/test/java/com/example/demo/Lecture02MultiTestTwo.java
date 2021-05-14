package com.example.demo;


import com.example.demo.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Lecture02MultiTestTwo  extends  DemoApplicationTests{
    @Autowired
    private WebClient webClient;

    @Test
    public void fluxTest(){
        Flux<Response> fluxResponse = webClient.get()
                .uri("reactive-math/table/{input}",5)
                .retrieve()
                .bodyToFlux(Response.class)
                .doOnNext(System.out::println);

        StepVerifier.create(fluxResponse)
                .expectNextCount(10)
                .verifyComplete();

    }

    @Test
    public void fluxStreamTest(){
        Flux<Response> fluxResponse = webClient.get()
                .uri("reactive-math/table/{input}/stream",5)
                .retrieve()
                .bodyToFlux(Response.class)
                .doOnNext(System.out::println);

        StepVerifier.create(fluxResponse)
                .expectNextCount(10)
                .verifyComplete();

    }
}
