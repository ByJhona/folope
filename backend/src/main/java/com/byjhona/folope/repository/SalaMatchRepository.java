package com.byjhona.folope.repository;

import com.byjhona.folope.domain.sala_match.SalaMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SalaMatchRepository extends JpaRepository<SalaMatch, Long> {

    @Query("""
            select EXISTS(
                select sala from sala_match sala
                where
                sala.id = ?#{#salaMatch.id}
            )
            """)
    boolean existeNoBanco(@Param("salaMatch") SalaMatch salaMatch);
}
