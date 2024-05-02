package com.byjhona.folope.repository;

import com.byjhona.folope.domain.relac_sala_match_filmes.RelacSalaMatchFilmes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RelacSalaMatchFilmesRepository extends JpaRepository<RelacSalaMatchFilmes, Long>
{

    @Query("SELECT filme FROM relac_sala_match_filmes filme WHERE filme.idSalaMatch = :idSalaMatch")
    List<RelacSalaMatchFilmes> listarFilmesMatch(@Param("idSalaMatch") Long idSalaMatch);

}

