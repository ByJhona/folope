package com.byjhona.folope.domain.sala_match;

import com.byjhona.folope.types.StatusSolicitacao;


public record SalaMatchDTO(
        Long id,
        Long idAnfitriao,
        Long idHospede,
        StatusSolicitacao status
) {

        public SalaMatchDTO(SalaMatch sm) {
                this(sm.getId(), sm.getIdAnfitriao(), sm.getIdHospede(), sm.getStatus());
        }
}
