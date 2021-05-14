package com.example.demo;

import com.example.demo.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lecture05BadRequestTest extends  DemoApplicationTests {

    @Autowired
    private WebClient webClient;

    @Test
    public void fluxTest() {
        Mono<Response> monoResponse = webClient.get()
                .uri("reactive-math/square/{input}/throw", 5)
                .retrieve()
                .bodyToMono(Response.class)
                .doOnNext(System.out::println)
                .doOnError(err->System.out.println(err.getMessage()));

        StepVerifier.create(monoResponse)
                .expectNextCount(1)
                .verifyError(WebClientResponseException.BadRequest.class);

    }
}
