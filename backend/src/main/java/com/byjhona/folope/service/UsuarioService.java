package com.byjhona.folope.service;

import com.byjhona.folope.domain.relac_usuario_filme_curtido.RelacUsuarioFilmeCurtido;
import com.byjhona.folope.domain.relac_usuario_genero_curtido.RelacUsuarioGeneroCurtido;
import com.byjhona.folope.domain.token.TokenDTO;
import com.byjhona.folope.domain.usuario.Usuario;
import com.byjhona.folope.domain.usuario.UsuarioCadastroDTO;
import com.byjhona.folope.domain.usuario.UsuarioDTO;
import com.byjhona.folope.domain.usuario.UsuarioLoginDTO;
import com.byjhona.folope.exception.NaoEncontradoException;
import com.byjhona.folope.exception.RelacaoExisteNoBancoException;
import com.byjhona.folope.repository.RelacUsuarioFilmeCurtidoRepository;
import com.byjhona.folope.repository.RelacUsuarioGeneroCurtidoRepository;
import com.byjhona.folope.repository.UsuarioRepository;
import com.byjhona.folope.service.autorizacao.AutenticacaoUsuario;
import com.byjhona.folope.service.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private RelacUsuarioFilmeCurtidoRepository usuarioFilmeCurtidoRepo;
    @Autowired
    private RelacUsuarioGeneroCurtidoRepository usuarioGeneroCurtidoRepo;
    @Autowired
    private UsuarioRepository usuarioRepo;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenServ;


    public void cadastrar(UsuarioCadastroDTO usuarioDTO) {
        String senhaEncriptada = new BCryptPasswordEncoder().encode(usuarioDTO.senha());
        Usuario usuario = new Usuario(usuarioDTO.nome(), usuarioDTO.email(), senhaEncriptada);
        usuarioRepo.save(usuario);
    }

    public TokenDTO entrar(UsuarioLoginDTO usuarioLoginDTO) {
        UsernamePasswordAuthenticationToken usuarioSenhaAuth = new UsernamePasswordAuthenticationToken(usuarioLoginDTO.nome(), usuarioLoginDTO.senha());
        Authentication autenticado = authenticationManager.authenticate(usuarioSenhaAuth);
        String token = tokenServ.gerarToken((AutenticacaoUsuario) autenticado.getPrincipal());
        return new TokenDTO(token);
    }

    public UsuarioDTO mostrar(Long id) {
        Usuario usuario = usuarioRepo.getReferenceById(id);
        return new UsuarioDTO(usuario);
    }

    public void cadastrarFilmeCurtido(RelacUsuarioFilmeCurtido filmeCurtido) {
        Long idUsuario = filmeCurtido.getIdUsuario();
        Usuario usuario = usuarioRepo.getReferenceById(idUsuario);
        boolean existeFilmeNoBanco = usuarioFilmeCurtidoRepo.existeNoBanco(filmeCurtido);

        try {
            usuarioRepo.existeNoBanco(usuario);
        } catch (Exception ex) {
            throw new NaoEncontradoException("O usúario de id: " + idUsuario + " não foi encontrado.");
        }

        if (!existeFilmeNoBanco) {
            usuarioFilmeCurtidoRepo.save(filmeCurtido);
        } else {
            throw new RelacaoExisteNoBancoException("O usuario com id: " + idUsuario + " já curtiu esse filme.");
        }
    }


    public void cadastrarGeneroCurtido(RelacUsuarioGeneroCurtido generoCur) {
        Long idUsuario = generoCur.getIdUsuario();
        Usuario usuario = usuarioRepo.getReferenceById(idUsuario);
        boolean existeGeneroNoBanco = usuarioGeneroCurtidoRepo.existeNoBanco(generoCur);

        try {
            usuarioRepo.existeNoBanco(usuario);
        } catch (Exception ex) {
            throw new NaoEncontradoException("O usúario de id: " + idUsuario + " não foi encontrado.");
        }

        if (!existeGeneroNoBanco) {
            usuarioGeneroCurtidoRepo.save(generoCur);
        } else {
            throw new RelacaoExisteNoBancoException("O usuario com id: " + idUsuario + " já curtiu esse genero.");
        }
    }
}
