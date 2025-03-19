package com.byjhona.folope.repository;

import com.byjhona.folope.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("""
            select EXISTS(
                select usuario from usuario usuario
                where
                usuario.email = ?#{#usuario.email}
            )
            """)
    boolean existeNoBanco(@Param("usuario") Usuario usuario);

    Usuario findByNome(String nome);
}