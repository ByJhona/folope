package com.byjhona.folope.domain.usuario;

import com.byjhona.folope.types.RoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "usuario")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Usuario {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "identificador")
    private String identificador;
    @Column(name = "nome")
    private String nome;
    @Column(name = "email")
    private String email;
    @Column(name = "senha")
    private String senha;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    public Usuario(String identificador, String nome, String email, String senha) {
        this.identificador = identificador;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.role = RoleEnum.USER;

    }

}
