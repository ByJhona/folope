package com.byjhona.folope.controller;

import com.byjhona.folope.domain.relac_sala_filme_match.RelacSalaFilmeMatch;
import com.byjhona.folope.domain.sala_match.SalaMatch;
import com.byjhona.folope.domain.sala_match.SalaMatchDTO;
import com.byjhona.folope.repository.RelacSalaFilmeMatchRepository;
import com.byjhona.folope.service.SalaMatchService;
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
        List<RelacSalaFilmeMatch> lista = relacSalaFilmeMatchRepository.listarFilmesMatch(id);
        return lista;
    }

    @DeleteMapping("/deletar/{id}")
    public void deletar(@PathVariable Long id) {
        salaMatchService.deletar(id);
    }
}
