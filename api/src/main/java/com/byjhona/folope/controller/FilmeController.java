package com.byjhona.folope.controller;

import com.byjhona.folope.service.TmdbAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/filme")
public class FilmeController {
    @Autowired
    private TmdbAPI tmdbAPI;

    @GetMapping("/buscar/{id}")
    public ResponseEntity<String> mostrar(@PathVariable Long id){
        String filme = tmdbAPI.buscarFilmePorId(id);
        return ResponseEntity.ok().body(filme);
    }
    @GetMapping("/buscar")
    public ResponseEntity<String> listar(@RequestParam(required = false) String sortear,
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
        String filmes = tmdbAPI.buscarFilmesPorParametros(parametros);
        return ResponseEntity.ok().body(filmes);
    }
}
