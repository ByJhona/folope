package com.byjhona.folope.autorizacao;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${folope.chave.criptografia}")
    private String chave;

    public String gerarToken(AutorizacaoUsuario usuario) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(this.chave);
            return JWT.create()
                    .withIssuer("API Folope")
                    .withSubject(usuario.getUsername())
                    .withExpiresAt(gerarTempoExpiracao())
                    .sign(algoritmo);
        } catch (JWTCreationException ex) {
            throw new RuntimeException("Erro enquanto gera a chave JWT", ex);
        }

    }

    public String validarToken(String token) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(this.chave);
            return JWT.require(algoritmo)
                    .withIssuer("API Folope")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException ex) {
            return "";
        }
    }

    private Instant gerarTempoExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
