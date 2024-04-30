package com.byjhona.folope.service;

import com.byjhona.folope.domain.relac_usuario_filme_curtido.RelacUsuarioFilmeCurtido;
import com.byjhona.folope.exception.RelacaoExistente;
import com.byjhona.folope.repository.RelacUsuarioFilmeCurtidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private RelacUsuarioFilmeCurtidoRepository relacUsuarioFilmeCurtidoRepository;

    public RelacUsuarioFilmeCurtido cadastrarFilmeCurtido(RelacUsuarioFilmeCurtido filmeCur) {
        RelacUsuarioFilmeCurtido novoFilmeCur;
        if (!relacUsuarioFilmeCurtidoRepository.existeNoBanco(filmeCur)) {
            novoFilmeCur = relacUsuarioFilmeCurtidoRepository.save(filmeCur);
        } else {
            throw new RelacaoExistente("O usuárío já gostou desse filme.");
        }
        return novoFilmeCur;
    }
}
