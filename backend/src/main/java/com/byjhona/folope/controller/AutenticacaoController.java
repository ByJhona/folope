package com.byjhona.folope.controller;

import com.byjhona.folope.domain.token.TokenDTO;
import com.byjhona.folope.domain.usuario.UsuarioCadastroDTO;
import com.byjhona.folope.domain.usuario.UsuarioLoginDTO;
import com.byjhona.folope.service.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<TokenDTO> entrar(@RequestBody UsuarioLoginDTO usuarioLoginDTO) {
        TokenDTO token = autenticacaoServ.entrar(usuarioLoginDTO);
        return ResponseEntity.ok().body(token);
    }


}
