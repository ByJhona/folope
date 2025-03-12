package com.byjhona.folope.service;

import com.byjhona.folope.domain.relac_usuario_filme_curtido.RelacUsuarioFilmeCurtido;
import com.byjhona.folope.domain.relac_usuario_genero_curtido.RelacUsuarioGeneroCurtido;
import com.byjhona.folope.domain.usuario.Usuario;
import com.byjhona.folope.domain.usuario.UsuarioDTO;
import com.byjhona.folope.domain.usuario.UsuarioLoginDTO;
import com.byjhona.folope.exception.NaoEncontradoException;
import com.byjhona.folope.exception.RelacaoExisteNoBancoException;
import com.byjhona.folope.repository.RelacUsuarioFilmeCurtidoRepository;
import com.byjhona.folope.repository.RelacUsuarioGeneroCurtidoRepository;
import com.byjhona.folope.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    private KeycloakService keycloakServ;

    public void cadastrar(Usuario usuario) {
        boolean existeNoBanco = usuarioRepo.existeNoBanco(usuario);
        if (!existeNoBanco) {
            usuarioRepo.save(usuario);
        } else {
            throw new RelacaoExisteNoBancoException("O usuario com e-mail: " + usuario.getEmail() + " já existe.");
        }
    }

    public String entrar(UsuarioLoginDTO usuarioLoginDTO) {
        String token = keycloakServ.gerarTokenEntrar(usuarioLoginDTO.nome(), usuarioLoginDTO.senha());
        System.out.printf(token);
        return token;
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
