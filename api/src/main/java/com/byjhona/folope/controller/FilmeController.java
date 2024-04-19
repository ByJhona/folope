package com.byjhona.folope.controller;

import com.byjhona.folope.domain.filme.Filme;
import com.byjhona.folope.domain.filme.FilmeDescobertaDTO;
import com.byjhona.folope.service.TmdbAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filme")
public class FilmeController {
    @Autowired
    private TmdbAPI tmdbAPI;

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Filme> mostrar(@PathVariable Long id){
        Filme filme = tmdbAPI.buscarFilmePorId(id);
        return ResponseEntity.ok().body(filme);
    }
    @GetMapping("/buscar")
    public ResponseEntity<List<FilmeDescobertaDTO>> listar(@RequestParam(required = false) String sortear,
                                                           @RequestParam(required = false) String genero,
                                                           @RequestParam(required = false) String idioma){
        String parametros = "";

        if(sortear != null){
            parametros += "sort_by=" + sortear + "&";
        }
        if(genero != null){
            parametros += "with_genres=" + genero + "&";
        }
        if(idioma != null){
            parametros+= "language=" + idioma + "&";
        }
        List<FilmeDescobertaDTO> filmes = tmdbAPI.buscarFilmesDescoberta(parametros);
        return ResponseEntity.ok().body(filmes);
    }
}
