package com.byjhona.folope.repository;

import com.byjhona.folope.domain.relac_sala_match_filme_curtido.RelacSalaMatchFilmeCurtido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RelacSalaMatchFilmeCurtidoRepository extends JpaRepository<RelacSalaMatchFilmeCurtido, Long> {

    @Query("SELECT filme FROM relac_sala_match_filme_curtido filme WHERE filme.idSalaMatch = :idSalaMatch")
    List<RelacSalaMatchFilmeCurtido> listarFilmesMatch(@Param("idSalaMatch") Long idSalaMatch);

}

