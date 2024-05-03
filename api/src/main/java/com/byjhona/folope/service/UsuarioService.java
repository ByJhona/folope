package com.byjhona.folope.service;

import com.byjhona.folope.domain.relac_usuario_filme_curtido.RelacUsuarioFilmeCurtido;
import com.byjhona.folope.domain.relac_usuario_genero_curtido.RelacUsuarioGeneroCurtido;
import com.byjhona.folope.domain.usuario.Usuario;
import com.byjhona.folope.domain.usuario.UsuarioDTO;
import com.byjhona.folope.exception.NaoEncontradoException;
import com.byjhona.folope.exception.RelacaoExisteNoBancoException;
import com.byjhona.folope.repository.RelacUsuarioFilmeCurtidoRepository;
import com.byjhona.folope.repository.RelacUsuarioGeneroCurtidoRepository;
import com.byjhona.folope.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public void cadastrar(Usuario usuario) {
        boolean existeNoBanco = usuarioRepo.existeNoBanco(usuario);
        if (!existeNoBanco) {
            usuarioRepo.save(usuario);

        } else {
            throw new RelacaoExisteNoBancoException();
        }
    }

    public UsuarioDTO mostrar(Long id) {
        Usuario usuario = usuarioRepo.getReferenceById(id);
        return new UsuarioDTO(usuario);
    }

    public void cadastrarFilmeCurtido(RelacUsuarioFilmeCurtido filmeCur) {
        if (!usuarioFilmeCurtidoRepo.existeNoBanco(filmeCur)) {
            usuarioFilmeCurtidoRepo.save(filmeCur);
        } else {
            throw new RelacaoExisteNoBancoException();
        }
    }
    public void cadastrarGeneroCurtido(RelacUsuarioGeneroCurtido generoCur) {
        Long idUsuario = generoCur.getIdUsuario();
        Usuario usuario = usuarioRepo.getReferenceById(idUsuario);
        boolean existeGeneroNoBanco = usuarioGeneroCurtidoRepo.existeNoBanco(generoCur);

        try{
            usuarioRepo.existeNoBanco(usuario);
        }catch (Exception ex){
            throw new NaoEncontradoException(idUsuario);
        }

        if (!existeGeneroNoBanco) {
            usuarioGeneroCurtidoRepo.save(generoCur);
        } else {
            throw new RelacaoExisteNoBancoException();
        }
    }
}
