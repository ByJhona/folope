package com.byjhona.folope.controller;

import com.byjhona.folope.domain.filme.FilmeDTO;
import com.byjhona.folope.domain.filme.FilmeDescobertaDTO;
import com.byjhona.folope.service.TmdbAPI;
import com.byjhona.folope.util.TratadorParametros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/filme")
public class FilmeController {
    @Autowired
    private TmdbAPI tmdbAPI;

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Mono<FilmeDTO>> buscarPorId(@PathVariable Long id) {
        Mono<FilmeDTO> filmeDTO = tmdbAPI.buscarFilmePorId(id);
        return ResponseEntity.ok().body(filmeDTO);
    }

    @GetMapping("/buscar")
    public ResponseEntity<Mono<List<FilmeDescobertaDTO>>> listar(@RequestParam(required = false) String sortear,
                                                                 @RequestParam(required = false) String genero
    ) {
        String parametros = TratadorParametros.tratar(sortear, genero);
        Mono<List<FilmeDescobertaDTO>> filmes = tmdbAPI.buscarFilmesDescoberta(parametros);
        return ResponseEntity.ok().body(filmes);
    }
}
