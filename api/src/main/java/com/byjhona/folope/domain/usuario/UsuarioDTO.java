package com.byjhona.folope.domain.usuario;

public record UsuarioDTO(
        Long id,
        String nome,
        String email,
        String senha

) {
    public UsuarioDTO(Usuario usuario) {
        this(usuario.getId(),usuario.getNome(), usuario.getEmail(), usuario.getSenha());
    }
}
