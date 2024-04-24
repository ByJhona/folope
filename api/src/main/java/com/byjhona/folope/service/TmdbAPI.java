package com.byjhona.folope.service;

import com.byjhona.folope.domain.filme.FilmeDTO;
import com.byjhona.folope.domain.filme.FilmeDescobertaDTO;
import com.byjhona.folope.domain.genero.Genero;
import com.byjhona.folope.exception.FilmeNaoEncontradoException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import net.bytebuddy.description.type.TypeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.JsonPath;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TmdbAPI {

    private final WebClient client;
    @Autowired
    private Gson gson;
    @Autowired
    private ObjectMapper json;

    public TmdbAPI(WebClient.Builder builder) {
        this.client = builder
                .baseUrl("https://api.themoviedb.org/3")
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.add("accept", "application/json");
                    httpHeaders.add("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzZTA0MTMwM2I5NTdhYjMyNzIyM2U2MmNhYzc3MmJmNCIsInN1YiI6IjY2MGQyZjAyZGE5ZWYyMDE3ZDU4MDdjNiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.VFrDqBe8dCon2WxBdkqbogTW77kMcQIf7j6lMsISbWE");
                })
                .build();
    }

    public Mono<FilmeDTO> buscarFilmePorId(Long id) {
        try {
            return client.get().uri("/movie/" + id + "?language=pt-BR")
                    .retrieve()
                    .bodyToMono(FilmeDTO.class);
        } catch (WebClientResponseException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new FilmeNaoEncontradoException("O filme com id: " + id + " não foi encontrado");
            } else {
                throw new RuntimeException("Erro ao encontrar filme com id: " + e.getMessage());
            }
        }
    }

    public Mono<List<FilmeDescobertaDTO>> buscarFilmesDescoberta(String parametros) {
        return client.get()
                .uri("/discover/movie?" + parametros + "&language=pt-BR")
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(filmesString -> {
                    try {
                        JsonNode raiz = json.readTree(filmesString);
                        JsonNode resultadosArray = raiz.path("results");
                        return Mono.just(json.readValue(resultadosArray.traverse(), new TypeReference<List<FilmeDescobertaDTO>>() {
                        }));
                    } catch (IOException e) {
                        return Mono.error(new RuntimeException(e));
                    }
                })
                .onErrorResume(WebClientResponseException.class, e -> Mono.error(new RuntimeException("Erro ao encontrar filmes: " + e.getMessage())));
    }

    public Mono<List<FilmeDescobertaDTO>> buscarFilmesDescoberta() {
        return client.get()
                .uri("/discover/movie?" + "&language=pt-BR")
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(filmesString -> {
                    try {
                        JsonNode raiz = json.readTree(filmesString);
                        JsonNode resultadosArray = raiz.path("results");
                        return Mono.just(json.readValue(resultadosArray.traverse(), new TypeReference<List<FilmeDescobertaDTO>>() {
                        }));
                    } catch (IOException e) {
                        return Mono.error(new RuntimeException(e));
                    }
                })
                .onErrorResume(WebClientResponseException.class, e -> Mono.error(new RuntimeException("Erro ao encontrar filmes: " + e.getMessage())));
    }

    public Mono<List<Genero>> buscarListaDeGeneros() {

        return client.get().uri("/genre/movie/list")
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(data -> {
                    try {
                        JsonNode raiz = json.readTree(data);
                        JsonNode resultadoArray = raiz.path("genres");
                        return Mono.just(json.readValue(resultadoArray.traverse(), new TypeReference<List<Genero>>() {
                        }));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

}
