package com.byjhona.folope.controller;

import com.byjhona.folope.domain.filme.Filme;
import com.byjhona.folope.domain.filme.FilmeDescobertaDTO;
import com.byjhona.folope.domain.sala_match.SalaMatch;
import com.byjhona.folope.domain.sala_match.SalaMatchDTO;
import com.byjhona.folope.domain.sala_match.relac_sala_filme_match.RelacSalaFilmeMatch;
import com.byjhona.folope.repository.RelacSalaFilmeMatchRepository;
import com.byjhona.folope.repository.SalaMatchRepository;
import com.byjhona.folope.service.SalaMatchService;
import com.byjhona.folope.service.TmdbAPI;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sala_match")
public class SalaMatchController {
    @Autowired
    private SalaMatchService salaMatchService;
    @Autowired
    RelacSalaFilmeMatchRepository relacSalaFilmeMatchRepository;

    @Transactional
    @PostMapping("/cadastrar")
    public ResponseEntity<SalaMatchDTO> cadastrar(@RequestBody SalaMatch salaMatch) {
        SalaMatchDTO salaMatchDTO = salaMatchService.cadastrar(salaMatch);
        return ResponseEntity.ok().body(salaMatchDTO);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<SalaMatchDTO> mostrar(@PathVariable Long id) {
        SalaMatchDTO salaMatchDTO = salaMatchService.mostrar(id);
        return ResponseEntity.ok().body(salaMatchDTO);
    }
    @GetMapping("/buscar/{id}/filmes")
    public List<RelacSalaFilmeMatch> mostrarFilmes(@PathVariable Long id) {
        List<RelacSalaFilmeMatch> lista = relacSalaFilmeMatchRepository.encontrarFilmesSala(id);
        return lista;
    }

    @DeleteMapping("/deletar/{id}")
    public void deletar(@PathVariable Long id) {
        salaMatchService.deletar(id);
    }
}
