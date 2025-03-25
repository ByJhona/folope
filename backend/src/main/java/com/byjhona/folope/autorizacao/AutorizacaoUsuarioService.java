package com.byjhona.folope.autorizacao;

import com.byjhona.folope.domain.usuario.Usuario;
import com.byjhona.folope.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutorizacaoUsuarioService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepo;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Usuario usuario = usuarioRepo.findByIdentificador(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));
        return new AutorizacaoUsuario(usuario);
    }
}
