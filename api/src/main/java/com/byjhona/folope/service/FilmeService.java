package com.byjhona.folope.service;

import com.byjhona.folope.controller.FilmeController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class FilmeService {

    private final WebClient client;

    public FilmeService(WebClient.Builder builder){
        this.client = builder
                .baseUrl("https://api.themoviedb.org/3")
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.add("accept", "application/json");
                    httpHeaders.add("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzZTA0MTMwM2I5NTdhYjMyNzIyM2U2MmNhYzc3MmJmNCIsInN1YiI6IjY2MGQyZjAyZGE5ZWYyMDE3ZDU4MDdjNiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.VFrDqBe8dCon2WxBdkqbogTW77kMcQIf7j6lMsISbWE");
                    })
                .build();
    }
    public Mono<String> buscarPorId(Long id){
        var filme = client.get().uri("/movie/" + id)
                .retrieve()
                .bodyToMono(String.class);
        return filme;
    }
}
