package com.rafael.itmanager.security;

import com.rafael.itmanager.model.Usuario;
import com.rafael.itmanager.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = service.obterPorLogin(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        String role = usuario.getAdmin() ? "ADMIN" : "USER";

        return User.builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(role)
                .build();
    }
}
