package com.byjhona.folope.domain.relac_sala_match_filme_curtido;


import jakarta.persistence.Column;

public record RelacSalaMatchFilmeCurtidoDTO(

        @Column(name = "id_sala_match")
        Long idSalaMatch,
        @Column(name = "id_usuario")
        Long idUsuario,
        @Column(name = "id_filme")
        Long idFilme) {

}



