package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Lec08HeadersTest extends  DemoApplicationTests{

    @Autowired
    private WebClient webClient;
    String queryString = "http://localhost:8080/jobs/search?count={count}&page={page}";

    @Test
    public void queryParams(){
        UriComponentsBuilder.fromUriString(queryString).build(10,20);

        Flux<Integer> fluxResponse = webClient
                    .get()
                     .uri(b -> b.path("jobs/search").query("count={count}&page={page}").build(10,20))
                    .retrieve()

                    .bodyToFlux(Integer.class)
                    .doOnNext(System.out::println);

        StepVerifier.create(fluxResponse)
                .expectNextCount(2).verifyComplete();

    }
}
