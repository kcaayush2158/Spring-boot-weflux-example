package com.example.demo.config;

import ch.qos.logback.core.net.server.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
public class WebclientConfig {

    @Bean
    public WebClient webClient(){
        return WebClient.builder()
                .baseUrl("http://localhost:8080")
                .filter(this::sessionToken)
                .build();
    }
//    private Mono<ClientResponse> sessionToken(ClientRequest request , ExchangeFunction exchangeFunction){
//        System.out.println("GENERATING SESSION TOKEN");
//         ClientRequest clientRequest = ClientRequest.from(request).headers(h->h.setBasicAuth("some-length-token")).build();
//    return exchangeFunction.exchange(clientRequest);
//    }

//    private Mono<ClientResponse> sessionToken(ClientRequest request , ExchangeFunction exchangeFunction){
//        System.out.println("GENERATING SESSION TOKEN");
//        ClientRequest clientRequest = ClientRequest.from(request).headers(h->h.setBasicAuth("some-length-token")).build();
//        return exchangeFunction.exchange(clientRequest);
//    }

    private Mono<ClientResponse> sessionToken(ClientRequest clientRequest,ExchangeFunction exchangeFunction){
        clientRequest.attribute("auth")
            .map(v -> v.equals("basic") ? withBasicAuth(clientRequest)   : withOuth(clientRequest));
        return exchangeFunction.exchange(clientRequest);

    }

    private  ClientRequest withBasicAuth(ClientRequest request){
        return ClientRequest.from(request)
                .headers(h -> h.setBasicAuth("username","password"))
                .build();
    }
    private ClientRequest withOuth(ClientRequest clientRequest){
        return ClientRequest.from(clientRequest)
                .headers(h -> h.setBearerAuth("some-token"))
                .build();
    }

}
