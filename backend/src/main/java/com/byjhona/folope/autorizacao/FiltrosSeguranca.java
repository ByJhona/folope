package com.byjhona.folope.autorizacao;

import com.byjhona.folope.domain.usuario.Usuario;
import com.byjhona.folope.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class FiltrosSeguranca extends OncePerRequestFilter {

    @Autowired
    private AutorizacaoUsuarioService autorizacaoUsuarioService;
    @Autowired
    private TokenService tokenServ;
    @Autowired
    private UsuarioRepository usuarioRepo;

    private Optional<String> recuperarToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    return Optional.of(cookie.getValue());
                }
            }
        }
        return Optional.empty();
    }

    private void adicionarCookie(HttpServletResponse response, String token) {
        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(3600);
        response.addCookie(cookie);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Optional<String> token = recuperarToken(request);

        if (token.isPresent()) {
            adicionarCookie(response, token.get());
            var nome = tokenServ.validarToken(token.get());
            Usuario usuario = usuarioRepo.findByIdentificador(nome).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));
            UserDetails userDetails = new AutorizacaoUsuario(usuario);
            Authentication autenticacao = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(autenticacao);
        }
        filterChain.doFilter(request, response);
    }
}
