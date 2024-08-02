package com.byjhona.folope.domain.relac_usuario_genero_curtido;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "relac_usuario_genero_curtido")
public class RelacUsuarioGeneroCurtido {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id_usuario")
    private Long idUsuario;
    @Column(name = "id_genero")
    private Long idGenero;

}
