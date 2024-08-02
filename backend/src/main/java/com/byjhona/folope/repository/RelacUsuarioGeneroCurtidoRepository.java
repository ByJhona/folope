package com.byjhona.folope.repository;

import com.byjhona.folope.domain.genero.Genero;
import com.byjhona.folope.domain.relac_usuario_genero_curtido.RelacUsuarioGeneroCurtido;
import com.byjhona.folope.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RelacUsuarioGeneroCurtidoRepository extends JpaRepository<RelacUsuarioGeneroCurtido, Long> {

    @Query("""
            select EXISTS(
                select genero from relac_usuario_genero_curtido genero
                where
                genero.idGenero = ?#{#genero.idGenero}
                and
                genero.idUsuario = ?#{#genero.idUsuario}
            )
            """)
    boolean existeNoBanco(@Param("genero") RelacUsuarioGeneroCurtido genero);

    @Query("select genero.idGenero from relac_usuario_genero_curtido genero where genero.idUsuario = :idUsuario")
    List<Long> generosCurtidos(@Param("idUsuario") Long idUsuario);
}
