package com.byjhona.folope.repository;

import com.byjhona.folope.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("""
            select EXISTS(
                select usuario from usuario usuario
                where
                usuario.email = :email
            )
            """)
    boolean emailExisteNoBanco(@Param("email") String email);


    @Query("""
            select EXISTS(
                select usuario from usuario usuario
                where
                usuario.identificador = :identificador
            )
            """)
    boolean identificadorExisteNoBanco(@Param("identificador") String identificador);

    UserDetails findByNome(String identificador);
}