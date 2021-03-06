package com.example.demo.controller;

import com.example.demo.dto.MultiplyRequestDTO;
import com.example.demo.dto.Response;
import com.example.demo.service.ReactiveMathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("reactive-math")
public class ReactiveMathController {
    @Autowired
    private ReactiveMathService reactiveMathService;


    @GetMapping("/square/{input}")
    public Mono<Response> findSquare(@PathVariable("input") int input){
        return this.reactiveMathService.findSquare(input);
    }

    @GetMapping("/table/{input}")
    public Flux<Response> multiplicationTable(@PathVariable("input") int input){
        return this.reactiveMathService.multiplicationTable(input);
    }

    @GetMapping(value = "/table/{input}/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Response> multiplicationTableStream(@PathVariable("input") int input){
        return this.reactiveMathService.multiplicationTable(input);
    }
    @PostMapping("multiply")
    public Mono<Response> multiply (@RequestBody Mono<MultiplyRequestDTO> requestDTOMono){
        return  this.reactiveMathService.multiply(requestDTOMono);

    }

}
