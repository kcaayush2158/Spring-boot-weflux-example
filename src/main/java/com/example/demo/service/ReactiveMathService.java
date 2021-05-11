package com.example.demo.service;

import com.example.demo.dto.MultiplyRequestDTO;
import com.example.demo.dto.Response;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ReactiveMathService {

    public Mono<Response> findSquare(int input){
        return Mono.fromSupplier(() -> input * input )
                .map(Response::new);
    }

    public Flux<Response> multiplicationTable(int input){
        return  Flux.range(1,10)
                .delayElements(Duration.ofSeconds(1))
//                .doOnNext(i -> SleepUtil.sleepSeconds(1))
                .doOnNext(i -> System.out.println("reactive math service is processing"))
                .map(i -> new Response(i *input));
    }

    public Mono<Response> multiply(Mono<MultiplyRequestDTO> dtoMono){
        return dtoMono.map( dto -> dto.getFirst() * dto.getSecond())
                .map(Response::new);
    }


}
