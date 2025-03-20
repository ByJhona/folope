package com.byjhona.folope.controller;

import com.byjhona.folope.domain.filme.FilmeDTO;
import com.byjhona.folope.domain.filme.FilmeDescobertaDTO;
import com.byjhona.folope.domain.filme.FilmeDescobertaResponse;
import com.byjhona.folope.service.TmdbAPIService;
import com.byjhona.folope.util.TratadorParametros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/filme")
public class FilmeController {
    @Autowired
    private TmdbAPIService tmdbAPIService;

    @GetMapping("/buscar/id")
    public ResponseEntity<FilmeDTO> buscarPorId(@RequestParam(required = true) Long id) {
        FilmeDTO filmeDTO = tmdbAPIService.buscarFilmePorId(id);
        return ResponseEntity.ok().body(filmeDTO);
    }

    @GetMapping("/buscar/titulo")
    public ResponseEntity<FilmeDescobertaResponse> buscarPorNome(@RequestParam(required = false) String titulo, @RequestParam(required = false) String pagina) {
        // colocar em termos de parametros
        String parametros = TratadorParametros.tratar(null, null, titulo, pagina);

        FilmeDescobertaResponse filmes = tmdbAPIService.buscarFilmesPorTitulo(parametros);
        return ResponseEntity.ok().body(filmes);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<FilmeDescobertaDTO>> listar(@RequestParam(required = false) String sortear,
                                                           @RequestParam(required = false) String genero,
                                                           @RequestParam(required = false) String query

    ) {
        String parametros = TratadorParametros.tratar(sortear, genero, query, null);
        List<FilmeDescobertaDTO> filmes = tmdbAPIService.buscarFilmesDescoberta(parametros);
        return ResponseEntity.ok().body(filmes);
    }
}
