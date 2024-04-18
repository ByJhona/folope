package com.byjhona.folope.service;

import com.byjhona.folope.domain.genero.Genero;
import com.byjhona.folope.domain.genero.ListadeGeneros;
import com.byjhona.folope.exception.FilmeNaoEncontradoException;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class TmdbAPI {

    private final WebClient client;

    public TmdbAPI(WebClient.Builder builder){
        this.client = builder
                .baseUrl("https://api.themoviedb.org/3")
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.add("accept", "application/json");
                    httpHeaders.add("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzZTA0MTMwM2I5NTdhYjMyNzIyM2U2MmNhYzc3MmJmNCIsInN1YiI6IjY2MGQyZjAyZGE5ZWYyMDE3ZDU4MDdjNiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.VFrDqBe8dCon2WxBdkqbogTW77kMcQIf7j6lMsISbWE");
                    })
                .build();
    }
    public String buscarFilmePorId(Long id){
        try{
            var filme = client.get().uri("/movie/" + id)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            return filme;
        }catch (WebClientResponseException e){
            if(e.getStatusCode() == HttpStatus.NOT_FOUND){
                throw new FilmeNaoEncontradoException("O filme com id: " + id + " não foi encontrado");
            }else {
                throw new RuntimeException("Erro ao encontrar filme com id: " + e.getMessage());
            }
        }
    }
    public String buscarFilmesPorParametros(String parametros){
        try{
            return client.get().uri("/discover/movie?" + parametros)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        }catch (WebClientResponseException e){
            throw new RuntimeException("Erro ao encontrar filmes" + e.getMessage());
        }
    }
    public String buscarListaDeGeneros(){
        try{
            return  client.get().uri("/genre/movie/list")
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        }catch (WebClientResponseException e){
            throw new RuntimeException("Erro ao buscar generos " + e.getMessage());
        }
    }
}
