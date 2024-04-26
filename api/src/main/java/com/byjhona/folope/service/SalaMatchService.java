package com.byjhona.folope.service;

import com.byjhona.folope.domain.filme.FilmeDescobertaDTO;
import com.byjhona.folope.domain.relac_sala_filme_match.RelacSalaFilmeMatch;
import com.byjhona.folope.domain.sala_match.SalaMatch;
import com.byjhona.folope.domain.sala_match.SalaMatchDTO;
import com.byjhona.folope.repository.RelacSalaFilmeMatchRepository;
import com.byjhona.folope.repository.RelacUsuarioFilmeCurtidoRepository;
import com.byjhona.folope.repository.RelacUsuarioGeneroCurtidoRepository;
import com.byjhona.folope.repository.SalaMatchRepository;
import com.byjhona.folope.util.TratadorParametros;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalaMatchService {
    @Autowired
    private SalaMatchRepository salaMatchRepository;
    @Autowired
    private RelacSalaFilmeMatchRepository relacSalaFilmeMatchRepository;
    @Autowired
    private TmdbAPI tmdbAPI;
    @Autowired
    private RelacUsuarioGeneroCurtidoRepository generoRepository;
    @Autowired
    private RelacUsuarioFilmeCurtidoRepository filmeRepository;

    @Transactional
    public SalaMatchDTO cadastrar(SalaMatch salaMatch) {
        salaMatchRepository.save(salaMatch);
        escolherFilmesSala(salaMatch);
        return new SalaMatchDTO(salaMatch);
    }

    @Transactional
    public void escolherFilmesSala(SalaMatch salaMatch) {
        Long idSalaMatch = salaMatch.getId();
        Long idAnfitriao = salaMatch.getIdAnfitriao();
        Long idHospede = salaMatch.getIdHospede();
        List<Long> generosAnfitriao = generoRepository.generosCurtidos(idAnfitriao);
        List<Long> generosHospede = generoRepository.generosCurtidos(idHospede);
        List<Long> generosMatch = new ArrayList<>();

        for (Long item : generosAnfitriao) {
            if (generosHospede.contains(item)) {
                generosMatch.add(item);
            }
        }
        String genero = generosMatch.stream().map(String::valueOf).collect(Collectors.joining(","));
        genero = TratadorParametros.tratar(null, genero);

        Long recomendacaoAnfitriao = filmeRepository.sortearFilmeAleatorio(idAnfitriao);
        Long recomendacaoHospede = filmeRepository.sortearFilmeAleatorio(idHospede);

        Mono<List<FilmeDescobertaDTO>> filmesRecomedacaoAnfitriao = null;
        Mono<List<FilmeDescobertaDTO>> filmesRecomedacaoHospede = null;
        Mono<List<FilmeDescobertaDTO>> filmesDescoberta = null;

        if (recomendacaoAnfitriao != null) {
            filmesRecomedacaoAnfitriao = tmdbAPI.buscarFilmesRecomendacao(recomendacaoAnfitriao);
            salvarFilmesNoBanco(filmesRecomedacaoAnfitriao, idSalaMatch);

        }
        if (recomendacaoHospede != null) {
            filmesRecomedacaoHospede = tmdbAPI.buscarFilmesRecomendacao(recomendacaoHospede);
            salvarFilmesNoBanco(filmesRecomedacaoHospede, idSalaMatch);
        }
        if (!genero.isEmpty()) {
            filmesDescoberta = tmdbAPI.buscarFilmesDescoberta(genero);
            salvarFilmesNoBanco(filmesDescoberta, idSalaMatch);
        }
    }

    private void salvarFilmesNoBanco(Mono<List<FilmeDescobertaDTO>> filmes, Long idSalaMatch) {
        filmes.subscribe(item -> {
            System.out.println(item);
            for (FilmeDescobertaDTO t : item) {
                Long idFilme = t.id();
                RelacSalaFilmeMatch relacSalaFilmeMatch = new RelacSalaFilmeMatch(idSalaMatch, idFilme);
                relacSalaFilmeMatchRepository.save(relacSalaFilmeMatch);
            }
        });
    }

    public SalaMatchDTO mostrar(Long id) {
        SalaMatch salaMatch = salaMatchRepository.getReferenceById(id);
        return new SalaMatchDTO(salaMatch);
    }

    public void deletar(Long id) {
        salaMatchRepository.deleteById(id);
    }
}
