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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
    public SalaMatchDTO cadastrar(SalaMatch salaMatch) throws IOException {
        salaMatchRepository.save(salaMatch);
        escolherFilmesSala(salaMatch);
        return new SalaMatchDTO(salaMatch);
    }

    // Fazer uma logica para nao colocar filmes duplicados
    @Transactional
    public void escolherFilmesSala(SalaMatch salaMatch) throws IOException {
        Long idSalaMatch = salaMatch.getId();
        Long idAnfitriao = salaMatch.getIdAnfitriao();
        Long idHospede = salaMatch.getIdHospede();
        String generos = generoMatchParaParametro(idAnfitriao, idHospede);

        Long recomendacaoAnfitriao = filmeRepository.sortearFilmeAleatorio(idAnfitriao);
        Long recomendacaoHospede = filmeRepository.sortearFilmeAleatorio(idHospede);

        List<FilmeDescobertaDTO> filmesRecomedacaoAnfitriao = tmdbAPI.buscarFilmesRecomendacao(recomendacaoAnfitriao);
        List<FilmeDescobertaDTO> filmesRecomedacaoHospede = tmdbAPI.buscarFilmesRecomendacao(recomendacaoHospede);
        List<FilmeDescobertaDTO> filmesDescoberta = tmdbAPI.buscarFilmesDescoberta(generos);
        List<FilmeDescobertaDTO> filmesSala = new ArrayList<FilmeDescobertaDTO>();

        filmesSala.addAll(filmesDescoberta);
        filmesSala.addAll(filmesRecomedacaoAnfitriao);
        filmesSala.addAll(filmesRecomedacaoHospede);

        List<FilmeDescobertaDTO> filmesTratadosSala = tratarListaFilmes(filmesSala);
        salvarFilmesNoBanco(filmesTratadosSala, idSalaMatch);
    }

    private String generoMatchParaParametro(Long idAnfitriao, Long idHospede) {
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
        return genero;

    }

    private List<FilmeDescobertaDTO> tratarListaFilmes(List<FilmeDescobertaDTO> filmesLista) throws IOException {
        int limiarFilmes = 80;
        int maxTentativas = 5;
        int maxFilmesRetornados = 50;
        int quantFilmes = filmesLista.size();
        List<FilmeDescobertaDTO> filmesTratados = filmesLista.stream().distinct().collect(Collectors.toList());
        System.out.println(filmesTratados.size());
        while (quantFilmes < limiarFilmes && maxTentativas > 0) {
            List<FilmeDescobertaDTO> filmesExtras = tmdbAPI.buscarFilmesDescoberta();
            filmesExtras = filmesExtras.stream().distinct().toList();
            filmesTratados.addAll(filmesExtras);
            quantFilmes = filmesTratados.size();
            maxTentativas--;
        }
        Collections.shuffle(filmesTratados);
        return filmesTratados.subList(0, maxFilmesRetornados);
    }


    private void salvarFilmesNoBanco(List<FilmeDescobertaDTO> filmes, Long idSalaMatch) {
        for (FilmeDescobertaDTO t : filmes) {
            Long idFilme = t.id();
            RelacSalaFilmeMatch relacSalaFilmeMatch = new RelacSalaFilmeMatch(idSalaMatch, idFilme);
            relacSalaFilmeMatchRepository.save(relacSalaFilmeMatch);
        }
    }

    public SalaMatchDTO mostrar(Long id) {
        SalaMatch salaMatch = salaMatchRepository.getReferenceById(id);
        return new SalaMatchDTO(salaMatch);
    }

    public void deletar(Long id) {
        salaMatchRepository.deleteById(id);
    }
}
