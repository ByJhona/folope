package com.byjhona.folope.domain.filme;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record FilmeDescobertaDTO(
        @JsonAlias("id")
        Long id,
        @JsonAlias("title")
        String titulo,
        @JsonAlias("backdrop_path")
        String urlCapaFundo,
        @JsonAlias("poster_path")
        String urlCapaPoster,
        @JsonAlias("genre_ids")
        int[] idGeneros,
        @JsonAlias("overview")
        String sinopse,
        @JsonAlias("release_date")
        String dataLancamento,
        @JsonAlias("vote_average")
        float nota
) {
}
