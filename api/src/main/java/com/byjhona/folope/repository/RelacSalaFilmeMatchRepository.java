package com.byjhona.folope.repository;

import com.byjhona.folope.domain.filme.FilmeDescobertaDTO;
import com.byjhona.folope.domain.relac_sala_filme_match.RelacSalaFilmeMatch;
import com.byjhona.folope.domain.relac_sala_filme_match.RelacSalaFilmeMatchDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RelacSalaFilmeMatchRepository extends JpaRepository<RelacSalaFilmeMatch, Long>
{

    @Query("SELECT filme FROM relac_sala_filme_match filme WHERE filme.idSalaMatch = :idSalaMatch")
    List<RelacSalaFilmeMatch> listarFilmesMatch(@Param("idSalaMatch") Long idSalaMatch);

}

