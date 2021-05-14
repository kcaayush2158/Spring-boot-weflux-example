package com.example.demo.controller;

import com.example.demo.dto.Response;
import com.example.demo.exception.InputValidationException;
import com.example.demo.service.ReactiveMathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("reactive-math")
public class ReactiveMathValidationController {

    @Autowired
    private ReactiveMathService reactiveMathService;


    @GetMapping("/square/{input}/throw")
    public Mono<Response> findSquare(@PathVariable("input") int input){
        if(input >= 10 || input  <=10 ){
            throw  new InputValidationException(input);
        }
        return this.reactiveMathService.findSquare(input);
    }



}
