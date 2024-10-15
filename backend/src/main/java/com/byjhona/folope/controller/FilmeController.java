package com.byjhona.folope.controller;

import com.byjhona.folope.domain.filme.FilmeDTO;
import com.byjhona.folope.domain.filme.FilmeDescobertaDTO;
import com.byjhona.folope.domain.filme.FilmeDescobertaResponse;
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

    @GetMapping("/buscar/id")
    public ResponseEntity<FilmeDTO> buscarPorId(@RequestParam(required = true) Long id) {
        FilmeDTO filmeDTO = tmdbAPI.buscarFilmePorId(id);
        return ResponseEntity.ok().body(filmeDTO);
    }
    @GetMapping("/buscar/titulo")
    public ResponseEntity<FilmeDescobertaResponse> buscarPorNome(@RequestParam(required = false) String titulo, @RequestParam(required = false) String pagina) {
        // colocar em termos de parametros
        String parametros = TratadorParametros.tratar(null, null, titulo, pagina);

        FilmeDescobertaResponse filmes = tmdbAPI.buscarFilmesPorTitulo(parametros);
        return ResponseEntity.ok().body(filmes);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<FilmeDescobertaDTO>> listar(@RequestParam(required = false) String sortear,
                                                           @RequestParam(required = false) String genero,
                                                           @RequestParam(required = false) String query

    ) {
        String parametros = TratadorParametros.tratar(sortear, genero, query, null);
        List<FilmeDescobertaDTO> filmes = tmdbAPI.buscarFilmesDescoberta(parametros);
        return ResponseEntity.ok().body(filmes);
    }
}
