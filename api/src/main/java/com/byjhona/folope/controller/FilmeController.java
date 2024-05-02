package com.byjhona.folope.controller;

import com.byjhona.folope.domain.filme.FilmeDTO;
import com.byjhona.folope.domain.filme.FilmeDescobertaDTO;
import com.byjhona.folope.service.TmdbAPI;
import com.byjhona.folope.util.TratadorParametros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filme")
public class FilmeController {
    @Autowired
    private TmdbAPI tmdbAPI;

    @GetMapping("/buscar/id/{id}")
    public ResponseEntity<FilmeDTO> buscarPorId(@PathVariable Long id) {
        FilmeDTO filmeDTO = tmdbAPI.buscarFilmePorId(id);
        return ResponseEntity.ok().body(filmeDTO);
    }
    @GetMapping("/buscar/titulo/{titulo}")
    public ResponseEntity<List<FilmeDescobertaDTO>> buscarPorNome(@PathVariable String titulo) {
        // colocar em termos de parametros
        String parametros = TratadorParametros.tratar(null, null, titulo);

        List<FilmeDescobertaDTO> filmes = tmdbAPI.buscarFilmesPorTitulo(parametros);
        return ResponseEntity.ok().body(filmes);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<FilmeDescobertaDTO>> listar(@RequestParam(required = false) String sortear,
                                                           @RequestParam(required = false) String genero,
                                                           @RequestParam(required = false) String query

    ) {
        String parametros = TratadorParametros.tratar(sortear, genero, query);
        List<FilmeDescobertaDTO> filmes = tmdbAPI.buscarFilmesDescoberta(parametros);
        return ResponseEntity.ok().body(filmes);
    }
}
