package com.byjhona.folope.repository;

import com.byjhona.folope.domain.filme.FilmeDescobertaDTO;
import com.byjhona.folope.domain.sala_match.relac_sala_filme_match.RelacSalaFilmeMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RelacSalaFilmeMatchRepository extends JpaRepository<RelacSalaFilmeMatch, Long>
{
    @Query("SELECT f FROM relac_sala_filme_match f WHERE f.idSalaMatch = :idSala")
    List<RelacSalaFilmeMatch> encontrarFilmesSala(Long idSala);
}

