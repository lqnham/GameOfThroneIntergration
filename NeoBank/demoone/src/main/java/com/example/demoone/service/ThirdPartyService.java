package com.example.demoone.service;

import com.example.demoone.dto.Characters;
import com.example.demoone.dto.Continents;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class ThirdPartyService {
    private final WebClient webClient;
    public ThirdPartyService(WebClient.Builder builder){
        this.webClient = builder.baseUrl("https://thronesapi.com/api/").build();
    }

    public Flux<Characters> fetchCharacters(){
        return this.webClient.get().uri("/v2/Characters").retrieve().bodyToFlux(Characters.class);
    }

    public Flux<Continents> fetchContinents(){
        return this.webClient.get().uri("/v2/Continents").retrieve().bodyToFlux(Continents.class);
    }

    public List<Characters> convertCharactersFluxToList(Flux<Characters> charactersFlux){
       return charactersFlux.collectList().block();
    }

    public List<Continents> convertContinentsFluxToList(Flux<Continents> continentsFlux){
        return continentsFlux.collectList().block();
    }
}
