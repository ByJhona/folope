package com.byjhona.folope.service;

import com.byjhona.folope.autorizacao.AutorizacaoUsuario;
import com.byjhona.folope.autorizacao.GerenciaCookie;
import com.byjhona.folope.autorizacao.TokenService;
import com.byjhona.folope.domain.usuario.Usuario;
import com.byjhona.folope.domain.usuario.UsuarioCadastroDTO;
import com.byjhona.folope.domain.usuario.UsuarioLoginDTO;
import com.byjhona.folope.exception.EmailExisteNoBancoException;
import com.byjhona.folope.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService {
    @Autowired
    private UsuarioRepository usuarioRepo;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenServ;


    public void cadastrar(UsuarioCadastroDTO usuarioDTO) {
        boolean emailDuplicado = usuarioRepo.emailExisteNoBanco(usuarioDTO.email());
        boolean identificadorDuplicado = usuarioRepo.identificadorExisteNoBanco(usuarioDTO.identificador());

        if (identificadorDuplicado) {
            throw new EmailExisteNoBancoException("O ID '%s' está sendo usado por outra pessoa", usuarioDTO.identificador());
        }

        if (emailDuplicado) {
            throw new EmailExisteNoBancoException("O email '%s' está sendo usado por outra pessoa", usuarioDTO.email());
        }

        String senhaEncriptada = new BCryptPasswordEncoder().encode(usuarioDTO.senha());
        Usuario usuario = new Usuario(usuarioDTO.identificador(), usuarioDTO.nome(), usuarioDTO.email(), senhaEncriptada);
        usuarioRepo.save(usuario);
    }

    public void entrar(UsuarioLoginDTO usuarioLoginDTO, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken usuarioSenhaAuth = new UsernamePasswordAuthenticationToken(usuarioLoginDTO.identificador(), usuarioLoginDTO.senha());
        Authentication autenticado = authenticationManager.authenticate(usuarioSenhaAuth);
        String token = tokenServ.gerarToken((AutorizacaoUsuario) autenticado.getPrincipal());

        GerenciaCookie.adicionarTokenCookie(response, token);
    }

    public Boolean validarCookie(HttpServletRequest response) {
        Optional<String> token = tokenServ.recuperarToken(response);

        if (token.isPresent()) {
            Optional<String> nomeUsuario = tokenServ.validarToken(token.get());
            return nomeUsuario.isPresent();
        }
        return false;
    }
}
