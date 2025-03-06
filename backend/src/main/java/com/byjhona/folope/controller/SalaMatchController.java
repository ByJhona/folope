package com.byjhona.folope.controller;

import com.byjhona.folope.domain.relac_sala_match_filme_curtido.RelacSalaMatchFilmeCurtido;
import com.byjhona.folope.domain.relac_sala_match_filme_curtido.RelacSalaMatchFilmeCurtidoDTO;
import com.byjhona.folope.domain.relac_sala_match_filmes.RelacSalaMatchFilmesDTO;
import com.byjhona.folope.domain.sala_match.SalaMatch;
import com.byjhona.folope.domain.sala_match.SalaMatchDTO;
import com.byjhona.folope.service.SalaMatchService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sala_match")
public class SalaMatchController {
    @Autowired
    private SalaMatchService salaMatchService;


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
    public ResponseEntity<List<RelacSalaMatchFilmesDTO>> listarFilmesSalaMatch(@PathVariable Long id) {
        List<RelacSalaMatchFilmesDTO> filmesSalaMatch = salaMatchService.listarFilmesSalaMatch(id);
        return ResponseEntity.ok().body(filmesSalaMatch);
    }

    @GetMapping("/buscar/{id}/filmes-curtidos")
    public ResponseEntity<List<RelacSalaMatchFilmeCurtidoDTO>> mostrarFilmesCurtidos(@PathVariable Long id) {
        List<RelacSalaMatchFilmeCurtidoDTO> filmesCurtidosDTO = salaMatchService.mostrarFilmesCurtidos(id);
        return ResponseEntity.ok().body(filmesCurtidosDTO);
    }

    @Transactional
    @PostMapping("/cadastrar/filme-curtido")
    public ResponseEntity<RelacSalaMatchFilmeCurtidoDTO> cadastrarFilmeCurtido(@RequestBody RelacSalaMatchFilmeCurtido filmeCurtido) {
        RelacSalaMatchFilmeCurtidoDTO filmeCurtidoDTO = salaMatchService.cadastrarFilmeCurtido(filmeCurtido);
        return ResponseEntity.ok().body(filmeCurtidoDTO);
    }

    @PatchMapping("/responder-convite")
    public ResponseEntity<HttpStatusCode> responderConviteMatch(@RequestParam(required = true) String resposta,
                                                                @RequestParam(required = true) Long idSala,
                                                                @RequestParam(required = true) Long idUsuario) {
        salaMatchService.responderConviteMatch(resposta, idSala, idUsuario);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<HttpStatusCode> deletar(@PathVariable Long id) {
        salaMatchService.deletar(id);
        return ResponseEntity.ok().build();
    }
}
