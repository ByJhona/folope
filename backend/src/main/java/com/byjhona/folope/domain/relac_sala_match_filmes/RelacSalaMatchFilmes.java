package com.byjhona.folope.domain.relac_sala_match_filmes;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "relac_sala_match_filmes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RelacSalaMatchFilmes {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id_sala_match")
    private Long idSalaMatch;
    @Column(name = "id_filme")
    private Long idFilme;

    public RelacSalaMatchFilmes(Long idSalaMatch, Long idFilme) {
        this.idSalaMatch = idSalaMatch;
        this.idFilme = idFilme;
    }
}



