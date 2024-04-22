package com.byjhona.folope.domain.sala_match;

import com.byjhona.folope.types.StatusSolicitacao;
import com.fasterxml.jackson.databind.annotation.EnumNaming;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "sala_match")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalaMatch {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id_anfitriao")
    private Long idAnfitriao;
    @Column(name = "id_hospede")
    private Long idHospede;
    @Enumerated(EnumType.STRING)
    @Column(name = "status_solicitacao")
    private StatusSolicitacao status;
}
