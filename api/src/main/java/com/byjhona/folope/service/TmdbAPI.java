package com.byjhona.folope.service;

import com.byjhona.folope.domain.filme.Filme;
import com.byjhona.folope.domain.filme.FilmeDescobertaDTO;
import com.byjhona.folope.exception.FilmeNaoEncontradoException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.HttpStatus;
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
    private ObjectMapper json = new ObjectMapper();

    public TmdbAPI(WebClient.Builder builder){
        this.client = builder
                .baseUrl("https://api.themoviedb.org/3")
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.add("accept", "application/json");
                    httpHeaders.add("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzZTA0MTMwM2I5NTdhYjMyNzIyM2U2MmNhYzc3MmJmNCIsInN1YiI6IjY2MGQyZjAyZGE5ZWYyMDE3ZDU4MDdjNiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.VFrDqBe8dCon2WxBdkqbogTW77kMcQIf7j6lMsISbWE");
                    })
                .build();
    }
    public Filme buscarFilmePorId(Long id){
        try{
            String filmeString =  client.get().uri("/movie/" + id + "?language=pt-BR")
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            return json.readValue(filmeString, Filme.class);
        }catch (WebClientResponseException e){
            if(e.getStatusCode() == HttpStatus.NOT_FOUND){
                throw new FilmeNaoEncontradoException("O filme com id: " + id + " não foi encontrado");
            }else {
                throw new RuntimeException("Erro ao encontrar filme com id: " + e.getMessage());
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public List<FilmeDescobertaDTO> buscarFilmesDescoberta( String parametros){
        try{
            String filmesString = client.get().uri("/discover/movie?" + parametros + "&language=pt-BR")
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            JsonNode raiz = json.readTree(filmesString);
            JsonNode resultadosArray = raiz.path("results");

            return json.readValue(resultadosArray.traverse(), new TypeReference<List<FilmeDescobertaDTO>>() {});

        }catch (WebClientResponseException e){
            throw new RuntimeException("Erro ao encontrar filmes" + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public List<FilmeDescobertaDTO> buscarFilmesDescoberta(){
        try{
            String filmesString = client.get().uri("/discover/movie?"+ "&language=pt-BR")
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            JsonNode raiz = json.readTree(filmesString);
            JsonNode resultadosArray = raiz.path("results");

            return json.readValue(resultadosArray.traverse(), new TypeReference<List<FilmeDescobertaDTO>>() {});

        }catch (WebClientResponseException e){
            throw new RuntimeException("Erro ao encontrar filmes" + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
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
