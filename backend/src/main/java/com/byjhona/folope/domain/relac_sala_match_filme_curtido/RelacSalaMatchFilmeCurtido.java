package com.byjhona.folope.domain.relac_sala_match_filme_curtido;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "relac_sala_match_filme_curtido")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RelacSalaMatchFilmeCurtido {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id_sala_match")
    private Long idSalaMatch;
    @Column(name = "id_usuario")
    private Long idUsuario;
    @Column(name = "id_filme")
    private Long idFilme;

    public RelacSalaMatchFilmeCurtido(Long idSalaMatch, Long idUsuario, Long idFilme) {
        this.idSalaMatch = idSalaMatch;
        this.idUsuario = idUsuario;
        this.idFilme = idFilme;
    }
}



