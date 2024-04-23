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


    public Filme(
            Long id,
            String titulo,
            String urlCapaFundo,
            String urlCapaPoster,
            List<Genero> generos,
            String sinopse,
            String dataLancamento,
            int duracao,
            float nota
    ) {
        this.id = id;
        this.titulo = titulo;
        this.urlCapaFundo = "https://image.tmdb.org/t/p/original" + urlCapaFundo;
        this.urlCapaPoster = "https://image.tmdb.org/t/p/original" + urlCapaPoster;
        this.generos = generos;
        this.sinopse = sinopse;
        this.dataLancamento = dataLancamento;
        this.duracao = duracao;
        this.nota = nota;
    }
}
