package com.byjhona.folope.controller;

import com.byjhona.folope.domain.relac_usuario_filme_curtido.RelacUsuarioFilmeCurtido;
import com.byjhona.folope.domain.relac_usuario_genero_curtido.RelacUsuarioGeneroCurtido;
import com.byjhona.folope.domain.usuario.Usuario;
import com.byjhona.folope.domain.usuario.UsuarioDTO;
import com.byjhona.folope.repository.RelacUsuarioFilmeCurtidoRepository;
import com.byjhona.folope.repository.RelacUsuarioGeneroCurtidoRepository;
import com.byjhona.folope.repository.UsuarioRepository;
import com.byjhona.folope.service.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RelacUsuarioGeneroCurtidoRepository relacUsuarioGeneroCurtidoRepository;
    @Autowired
    private UsuarioService usuarioService;



    @PostMapping("/cadastrar")
    public ResponseEntity cadastrar(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario(usuarioDTO);
        usuarioRepository.save(usuario);
        return ResponseEntity.ok().body(usuarioDTO);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity mostrar(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.getReferenceById(id);
        UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
        return ResponseEntity.ok().body(usuarioDTO);
    }

    @Transactional
    @PostMapping("/curtir/genero")
    public ResponseEntity<RelacUsuarioGeneroCurtido> cadastrarGeneroCurtido(@RequestBody RelacUsuarioGeneroCurtido relacao) {
        RelacUsuarioGeneroCurtido novaRelacao = relacUsuarioGeneroCurtidoRepository.save(relacao);
        return ResponseEntity.ok().body(novaRelacao);
    }

    @Transactional
    @PostMapping("/curtir/filme")
    public ResponseEntity<RelacUsuarioFilmeCurtido> cadastrarFilmeCurtido(@RequestBody RelacUsuarioFilmeCurtido filmeCur) {
        RelacUsuarioFilmeCurtido filmeCurtido = usuarioService.cadastrarFilmeCurtido(filmeCur);
        return ResponseEntity.ok().body(filmeCurtido);
    }
}
