package com.byjhona.folope.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class KeycloakService {
    private final WebClient client;

    public KeycloakService(@Value("${folope.url.keycloak}") String uri, WebClient.Builder builder) {
        this.client = builder.baseUrl(uri).build();
    }

    public String gerarTokenEntrar(String nome, String senha) {
        MultiValueMap<String, String> corpo = new LinkedMultiValueMap<>();
        corpo.add("client_id", "folope-client");
        corpo.add("client_secret", "n68bIgiDDZLpyftARXom6Tz2lE4eMOPV");
        corpo.add("grant_type", "password");
        corpo.add("username", nome);
        corpo.add("password", senha);

        return this.client.post()
                .uri("/protocol/openid-connect/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(corpo))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}
