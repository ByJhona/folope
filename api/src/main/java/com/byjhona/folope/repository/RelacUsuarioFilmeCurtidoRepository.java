package com.byjhona.folope.repository;

import com.byjhona.folope.domain.filme.FilmeDescobertaDTO;
import com.byjhona.folope.domain.relac_usuario_filme_curtido.RelacUsuarioFilmeCurtido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;


public interface RelacUsuarioFilmeCurtidoRepository extends JpaRepository<RelacUsuarioFilmeCurtido, Long> {

    @Query("""
            select EXISTS(
                select l from relac_usuario_filme_curtido l
                where
                l.idUsuario = ?#{#filmeCur.idUsuario}
                and
                l.idFilme = ?#{#filmeCur.idFilme}
            )
            """)
    boolean existeNoBanco(@Param("filmeCur") RelacUsuarioFilmeCurtido filmeCur);

    @Query("SELECT filme.idFilme FROM relac_usuario_filme_curtido filme WHERE filme.idUsuario = :idUsuario ORDER BY RANDOM() LIMIT 1")
    Long sortearFilmeAleatorio(@Param("idUsuario") Long idUsuario);
}
