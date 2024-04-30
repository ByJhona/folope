package com.byjhona.folope.controller;

import com.byjhona.folope.domain.relac_sala_filme_match.RelacSalaFilmeMatch;
import com.byjhona.folope.domain.relac_sala_filme_match.RelacSalaFilmeMatchDTO;
import com.byjhona.folope.domain.sala_match.SalaMatch;
import com.byjhona.folope.domain.sala_match.SalaMatchDTO;
import com.byjhona.folope.repository.RelacSalaFilmeMatchRepository;
import com.byjhona.folope.service.SalaMatchService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public ResponseEntity<List<RelacSalaFilmeMatchDTO>> mostrarFilmes(@PathVariable Long id) {
        List<RelacSalaFilmeMatch> salaFilmesMatch = relacSalaFilmeMatchRepository.listarFilmesMatch(id);

        List<RelacSalaFilmeMatchDTO> salaFilmesMatchDTO = new ArrayList<>();
        for(RelacSalaFilmeMatch dados : salaFilmesMatch){
            RelacSalaFilmeMatchDTO salaFilmeMatch = new RelacSalaFilmeMatchDTO(dados.getIdSalaMatch(), dados.getIdFilme());
            salaFilmesMatchDTO.add(salaFilmeMatch);
        }
        return ResponseEntity.ok().body(salaFilmesMatchDTO);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletar(@PathVariable Long id) {
        salaMatchService.deletar(id);
    }
}
