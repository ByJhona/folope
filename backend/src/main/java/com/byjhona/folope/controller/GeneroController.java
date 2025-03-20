package com.byjhona.folope.controller;

import com.byjhona.folope.domain.genero.Genero;
import com.byjhona.folope.service.TmdbAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/genero")
public class GeneroController {
    @Autowired
    private TmdbAPIService tmdbAPIService;

    @GetMapping("/buscar")
    public ResponseEntity<List<Genero>> buscar() throws IOException {
        List<Genero> generos = tmdbAPIService.buscarListaDeGeneros();
        return ResponseEntity.ok().body(generos);
    }
}
