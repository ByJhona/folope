package com.byjhona.folope.domain.relac_sala_match_filmes;


import jakarta.persistence.Column;

public record RelacSalaMatchFilmesDTO(

        @Column(name = "id_sala_match")
        Long idSalaMatch,
        @Column(name = "id_filme")
        Long idFilme) {

}



