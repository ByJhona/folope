package com.byjhona.folope.service;

import com.byjhona.folope.domain.filme.FilmeDescobertaDTO;
import com.byjhona.folope.domain.sala_match.SalaMatch;
import com.byjhona.folope.domain.sala_match.SalaMatchDTO;
import com.byjhona.folope.domain.sala_match.relac_sala_filme_match.RelacSalaFilmeMatch;
import com.byjhona.folope.repository.RelacSalaFilmeMatchRepository;
import com.byjhona.folope.repository.SalaMatchRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaMatchService {
    @Autowired
    private SalaMatchRepository salaMatchRepository;
    @Autowired
    private RelacSalaFilmeMatchRepository relacSalaFilmeMatchRepository;
    @Autowired
    private TmdbAPI tmdbAPI;

    @Transactional
    public SalaMatchDTO cadastrar(SalaMatch salaMatch) {
        salaMatchRepository.save(salaMatch);
        escolherFilmesSala(salaMatch.getId());
        return new SalaMatchDTO(salaMatch);
    }

    public void escolherFilmesSala(Long idSalaMatch) {
        List<FilmeDescobertaDTO> lista = tmdbAPI.buscarFilmesDescoberta();
        for (FilmeDescobertaDTO item : lista) {
            Long idFilme = item.id();
            RelacSalaFilmeMatch relacSalaFilmeMatch = new RelacSalaFilmeMatch(idSalaMatch, idFilme);
            relacSalaFilmeMatchRepository.save(relacSalaFilmeMatch);
        }
    }

    public SalaMatchDTO mostrar(Long id) {
        SalaMatch salaMatch = salaMatchRepository.getReferenceById(id);
        return new SalaMatchDTO(salaMatch);
    }

    public void deletar(Long id){
        salaMatchRepository.deleteById(id);
    }
}
