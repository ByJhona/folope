package com.byjhona.folope.domain.relac_sala_filme_match;


import com.byjhona.folope.domain.sala_match.SalaMatchDTO;
import com.byjhona.folope.types.StatusSolicitacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "relac_sala_filme_match")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RelacSalaFilmeMatch {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id_sala_match")
    private Long idSalaMatch;
    @Column(name = "id_filme")
    private Long idFilme;

    public RelacSalaFilmeMatch(Long idSalaMatch, Long idFilme) {
        this.idSalaMatch = idSalaMatch;
        this.idFilme = idFilme;
    }
}



