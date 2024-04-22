package com.byjhona.folope.domain.sala_match;

import com.byjhona.folope.domain.filme.FilmeDescobertaDTO;
import com.byjhona.folope.domain.sala_match.relac_sala_filme_match.RelacSalaFilmeMatch;
import com.byjhona.folope.types.StatusSolicitacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


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
