package com.byjhona.folope.domain.usuario;

public record UsuarioDTO(
        Long id,
        String nome,
        String email
) {
    public UsuarioDTO(Usuario usuario) {
        this(usuario.getId(),usuario.getNome(), usuario.getEmail());
    }
}
