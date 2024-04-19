package com.byjhona.folope.domain.filme;

import com.byjhona.folope.domain.genero.Genero;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Filme {
    @JsonAlias("id")
    private Long id;
    @JsonAlias("title")
    private String titulo;
    @JsonAlias("backdrop_path")
    private String urlCapaFundo;
    @JsonAlias("poster_path")
    private String urlCapaPoster;
    @JsonAlias("genres")
    private List<Genero> generos;
    @JsonAlias("overview")
    private String sinopse;
    @JsonAlias("release_date")
    private String dataLancamento;
    @JsonAlias("runtime")
    private int duracao;
    @JsonAlias("vote_average")
    private float nota;
}
