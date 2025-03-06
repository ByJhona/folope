package com.byjhona.folope.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seguranca")
public class SegurancaController {

    @GetMapping("/publico")
    public String mostrarHtmlpublico() {
        return "<h1>Exibição da pagina pública<h1>";
    }

    @GetMapping("/privado")
    public String mostrarHtmlprivado(@AuthenticationPrincipal OidcUser usuario) {

        return "<h1>Exibição da pagina privada. <h1>" + usuario.getIdToken().getTokenValue();
    }

    @GetMapping("/jwt")
    public String mostrarHtmlprivadoJwt(@AuthenticationPrincipal Jwt usuario) {
        return usuario.getClaimAsString("email");
    }
}
