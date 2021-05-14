package com.example.demo;

import com.example.demo.dto.MultiplyRequestDTO;
import com.example.demo.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lec03PostRequestTest  extends  DemoApplicationTests{

    @Autowired
    private WebClient webClient;

    @Test
    public void postTest(){
        Mono<Response>  responseMono =this.webClient
                .post()
                .uri("reactive-math/multiply")
                .bodyValue(buildRequestDTO(5,2))
                .retrieve()
                .bodyToMono(Response.class)
                .doOnNext(System.out::println);

        StepVerifier.create(responseMono)
                .expectNextCount(1).verifyComplete();
    }

    private MultiplyRequestDTO buildRequestDTO(int a , int b){
        MultiplyRequestDTO multiplyRequestDTO = new MultiplyRequestDTO();
        multiplyRequestDTO.setFirst(a);
        multiplyRequestDTO.setSecond(b);
        return multiplyRequestDTO;

    }
}