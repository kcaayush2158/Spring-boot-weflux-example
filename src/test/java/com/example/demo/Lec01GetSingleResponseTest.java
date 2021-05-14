package com.example.demo;

import com.example.demo.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lec01GetSingleResponseTest  extends DemoApplicationTests{
    @Autowired
    private WebClient webClient;

    @Test
    public void blockTest(){
      Mono<Response> responseMono =  this.webClient
                .get()
                .uri("reactive-math/square/{input}",5)
                .retrieve()
                .bodyToMono(Response.class);



        StepVerifier.create(responseMono)

                .expectNextMatches(r -> r.getOutput() == 25)

                    .verifyComplete();

    }
}
