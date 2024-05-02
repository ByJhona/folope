package com.byjhona.folope.controller;

import com.byjhona.folope.domain.relac_sala_match_filme_curtido.RelacSalaMatchFilmeCurtido;
import com.byjhona.folope.domain.relac_sala_match_filme_curtido.RelacSalaMatchFilmeCurtidoDTO;
import com.byjhona.folope.domain.relac_sala_match_filmes.RelacSalaMatchFilmes;
import com.byjhona.folope.domain.relac_sala_match_filmes.RelacSalaMatchFilmesDTO;
import com.byjhona.folope.domain.sala_match.SalaMatch;
import com.byjhona.folope.domain.sala_match.SalaMatchDTO;
import com.byjhona.folope.repository.RelacSalaMatchFilmeCurtidoRepository;
import com.byjhona.folope.repository.RelacSalaMatchFilmesRepository;
import com.byjhona.folope.repository.SalaMatchRepository;
import com.byjhona.folope.service.SalaMatchService;
import com.byjhona.folope.types.StatusSolicitacao;
import jakarta.transaction.Transactional;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sala_match")
public class SalaMatchController {
    @Autowired
    private SalaMatchService salaMatchService;
    @Autowired
    RelacSalaMatchFilmesRepository relacSalaMatchFilmesRepository;
    @Autowired
    RelacSalaMatchFilmeCurtidoRepository relacSalaMatchFilmeCurtidoRepository;
    @Autowired
    SalaMatchRepository repo;

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
    public ResponseEntity<List<RelacSalaMatchFilmesDTO>> mostrarFilmes(@PathVariable Long id) {
        List<RelacSalaMatchFilmes> salaFilmesMatch = relacSalaMatchFilmesRepository.listarFilmesMatch(id);

        List<RelacSalaMatchFilmesDTO> salaFilmesMatchDTO = new ArrayList<>();
        for (RelacSalaMatchFilmes dados : salaFilmesMatch) {
            RelacSalaMatchFilmesDTO salaFilmeMatch = new RelacSalaMatchFilmesDTO(dados.getIdSalaMatch(), dados.getIdFilme());
            salaFilmesMatchDTO.add(salaFilmeMatch);
        }
        return ResponseEntity.ok().body(salaFilmesMatchDTO);
    }

    @GetMapping("/buscar/{id}/filmes-curtidos")
    public ResponseEntity<List<RelacSalaMatchFilmeCurtidoDTO>> mostrarFilmesCurtidos(@PathVariable Long id) {
        List<RelacSalaMatchFilmeCurtido> salaFilmesMatch = relacSalaMatchFilmeCurtidoRepository.listarFilmesMatch(id);
        List<RelacSalaMatchFilmeCurtidoDTO> salaMatchFilmeCurtidoDTO = new ArrayList<>();
        for (RelacSalaMatchFilmeCurtido dados : salaFilmesMatch) {
            RelacSalaMatchFilmeCurtidoDTO salaFilmeMatch = new RelacSalaMatchFilmeCurtidoDTO(dados.getIdSalaMatch(), dados.getIdUsuario(), dados.getIdFilme());
            salaMatchFilmeCurtidoDTO.add(salaFilmeMatch);
        }
        return ResponseEntity.ok().body(salaMatchFilmeCurtidoDTO);
    }

    @Transactional
    @PostMapping("/cadastrar/filme-curtido")
    public ResponseEntity<RelacSalaMatchFilmeCurtidoDTO> cadastrarFilmeCurtido(@RequestBody RelacSalaMatchFilmeCurtido filmeCurtido) {
        RelacSalaMatchFilmeCurtidoDTO filmeCurtidoDTO = new RelacSalaMatchFilmeCurtidoDTO(filmeCurtido.getIdSalaMatch(), filmeCurtido.getIdUsuario(), filmeCurtido.getIdFilme());
        relacSalaMatchFilmeCurtidoRepository.save(filmeCurtido);
        return ResponseEntity.ok().body(filmeCurtidoDTO);
    }

    @PatchMapping("/responder-convite")
    public void responderConviteMatch(@RequestParam(required = true) String resposta,
                                      @RequestParam(required = true) Long idSala,
                                      @RequestParam(required = true) Long idUsuario) {
        SalaMatch sala = repo.getReferenceById(idSala);

        if(sala.getIdHospede() != idUsuario){
            throw new RuntimeException();
        }// Criar excessao personalizada para dizer que o usuario nao tem permissao hehe

        if (resposta.equals(StatusSolicitacao.Aceito.toString()) || resposta.equals(StatusSolicitacao.Recusado.toString())) {
            sala.setStatus(resposta);
            repo.save(sala);
        }
    }

    @DeleteMapping("/deletar/{id}")
    public void deletar(@PathVariable Long id) {
        salaMatchService.deletar(id);
    }
}
