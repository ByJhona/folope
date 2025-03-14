package com.byjhona.folope.controller;

import com.byjhona.folope.domain.relac_usuario_filme_curtido.RelacUsuarioFilmeCurtido;
import com.byjhona.folope.domain.relac_usuario_genero_curtido.RelacUsuarioGeneroCurtido;
import com.byjhona.folope.domain.usuario.Usuario;
import com.byjhona.folope.domain.usuario.UsuarioDTO;
import com.byjhona.folope.domain.usuario.UsuarioLoginDTO;
import com.byjhona.folope.service.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;


    @PostMapping("/cadastrar")
    public ResponseEntity<HttpStatus> cadastrar(@RequestBody Usuario usuario) {
        usuarioService.cadastrar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public String entrar(@RequestBody UsuarioLoginDTO usuarioLoginDTO) {
        return usuarioService.entrar(usuarioLoginDTO);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<UsuarioDTO> mostrar(@PathVariable Long id) {
        UsuarioDTO usuarioDTO = usuarioService.mostrar(id);
        return ResponseEntity.ok().body(usuarioDTO);
    }

    @Transactional
    @PostMapping("/curtir/genero")
    public ResponseEntity<HttpStatus> cadastrarGeneroCurtido(@RequestBody RelacUsuarioGeneroCurtido generoCurtido) {
        usuarioService.cadastrarGeneroCurtido(generoCurtido);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Transactional
    @PostMapping("/curtir/filme")
    public ResponseEntity<HttpStatus> cadastrarFilmeCurtido(@RequestBody RelacUsuarioFilmeCurtido filmeCurtido) {
        usuarioService.cadastrarFilmeCurtido(filmeCurtido);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
