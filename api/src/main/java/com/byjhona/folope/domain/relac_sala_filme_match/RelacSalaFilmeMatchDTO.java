package com.byjhona.folope.domain.relac_sala_filme_match;


import jakarta.persistence.Column;

import java.util.List;

public record RelacSalaFilmeMatchDTO(

        @Column(name = "id_sala_match")
        Long idSalaMatch,
        @Column(name = "id_filme")
        Long idFilme) {

}



