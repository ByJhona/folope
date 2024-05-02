package com.byjhona.folope.service;

import com.byjhona.folope.domain.filme.FilmeDTO;
import com.byjhona.folope.domain.filme.FilmeDescobertaDTO;
import com.byjhona.folope.domain.genero.Genero;
import com.byjhona.folope.exception.NaoEncontradoException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.io.IOException;
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

    public FilmeDTO buscarFilmePorId(Long id) {
        try {
            return client.get().uri("/movie/" + id + "?language=pt-BR")
                    .retrieve()
                    .bodyToMono(FilmeDTO.class)
                    .block();
        } catch (WebClientResponseException ex) {
            throw new NaoEncontradoException(id);
        }
    }

    public List<FilmeDescobertaDTO> buscarFilmesPorTitulo(String parametros) {
        System.out.println(parametros);
        String filmesString = client.get()
                .uri("/search/movie" + parametros)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        try {
            JsonNode raiz = json.readTree(filmesString);
            JsonNode resultadosArray = raiz.path("results");
            return json.readValue(resultadosArray.traverse(), new TypeReference<List<FilmeDescobertaDTO>>() {
            });
        } catch (IOException ignored) {
        }
        return null;
    }


    public List<FilmeDescobertaDTO> buscarFilmesDescoberta(String parametros) {
        System.out.println(parametros);
        String filmesString = client.get()
                .uri("/discover/movie" + parametros)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        try {
            JsonNode raiz = json.readTree(filmesString);
            JsonNode resultadosArray = raiz.path("results");
            return json.readValue(resultadosArray.traverse(), new TypeReference<List<FilmeDescobertaDTO>>() {
            });
        } catch (IOException ignored) {
        }
        return null;
    }

    public List<FilmeDescobertaDTO> buscarFilmesDescoberta() {
        String filmesString = client.get()
                .uri("/discover/movie")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        try {
            JsonNode raiz = json.readTree(filmesString);
            JsonNode resultadosArray = raiz.path("results");
            return json.readValue(resultadosArray.traverse(), new TypeReference<List<FilmeDescobertaDTO>>() {
            });
        } catch (IOException ignored) {
        }
        return null;
    }

    public List<FilmeDescobertaDTO> buscarFilmesRecomendacao(Long idFilme) {
        String filmesString = client.get()
                .uri("/movie/" + idFilme + "/recommendations?" + "language=pt-BR")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        try {
            JsonNode raiz = json.readTree(filmesString);
            JsonNode resultadosArray = raiz.path("results");
            return json.readValue(resultadosArray.traverse(), new TypeReference<List<FilmeDescobertaDTO>>() {
            });
        } catch (IOException ignored) {
        }
        return null;
    }

    public List<Genero> buscarListaDeGeneros() {
        String generosString = client.get().uri("/genre/movie/list")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        try {
            JsonNode raiz = json.readTree(generosString);
            JsonNode resultadoArray = raiz.path("genres");
            return json.readValue(resultadoArray.traverse(), new TypeReference<List<Genero>>() {
            });
        } catch (IOException ignored) {

        }
        return null;
    }
}