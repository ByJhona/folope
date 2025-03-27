package com.byjhona.folope.controller;

import com.byjhona.folope.domain.usuario.UsuarioCadastroDTO;
import com.byjhona.folope.domain.usuario.UsuarioLoginDTO;
import com.byjhona.folope.service.AutenticacaoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutenticacaoController {
    @Autowired
    private AutenticacaoService autenticacaoServ;

    @PostMapping("/cadastrar")
    public ResponseEntity<HttpStatus> cadastrar(@RequestBody UsuarioCadastroDTO usuario) {
        autenticacaoServ.cadastrar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<HttpStatus> entrar(@RequestBody UsuarioLoginDTO usuarioLoginDTO, HttpServletResponse response) {
        autenticacaoServ.entrar(usuarioLoginDTO, response);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/usuario-autenticado")
    public ResponseEntity<Boolean> verificarUsuarioAutenticado(HttpServletRequest response) {
        Boolean autenticado = autenticacaoServ.validarCookie(response);
        return ResponseEntity.ok().body(autenticado);
    }


}
