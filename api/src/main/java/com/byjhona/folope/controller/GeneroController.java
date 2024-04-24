package com.byjhona.folope.controller;

import com.byjhona.folope.domain.genero.Genero;
import com.byjhona.folope.domain.relac_usuario_genero_curtido.RelacUsuarioGeneroCurtido;
import com.byjhona.folope.repository.RelacUsuarioGeneroCurtidoRepository;
import com.byjhona.folope.service.TmdbAPI;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/genero")
public class GeneroController {
    @Autowired
    private TmdbAPI tmdbAPI;
    @Autowired
    private RelacUsuarioGeneroCurtidoRepository relacUsuarioGeneroCurtidoRepository;

    @GetMapping("/buscar")
    public ResponseEntity<Mono<List<Genero>>> mostrar(){
        Mono<List<Genero>> generos = tmdbAPI.buscarListaDeGeneros();
        return ResponseEntity.ok().body(generos);
    }
    @Transactional
    @PostMapping("/curtir")
    public ResponseEntity<RelacUsuarioGeneroCurtido> cadastrarGeneroCurtido(@RequestBody RelacUsuarioGeneroCurtido relacao){
        RelacUsuarioGeneroCurtido novaRelacao = relacUsuarioGeneroCurtidoRepository.save(relacao);
        return ResponseEntity.ok().body(novaRelacao);
    }
}
