package com.byjhona.folope.domain.usuario;

public record UsuarioDTO(
        Long id,
        String identificador,
        String nome,
        String email
) {
    public UsuarioDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getIdentificador(), usuario.getNome(), usuario.getEmail());
    }
}
