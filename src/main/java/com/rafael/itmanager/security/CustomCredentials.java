package com.rafael.itmanager.security;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class CustomCredentials  implements Authentication {

    @Autowired
    private IdentificacaoUsuario identificacaoUsuario;

    public CustomCredentials(IdentificacaoUsuario identificacaoUsuario) {
        if (identificacaoUsuario == null) {
            throw new ExceptionInInitializerError
                    ("Não é possível criar um customAuthentication sem a identificação do usuário");
        }
        this.identificacaoUsuario = identificacaoUsuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.identificacaoUsuario
                .getPermissoes()
                .stream()
                .map(perm -> new SimpleGrantedAuthority(perm))
                .collect(Collectors.toList());
    }

    @Override
    public @Nullable Object getCredentials() {
        return null;
    }

    @Override
    public String getName() {
        return this.identificacaoUsuario.getLogin();
    }

    @Override
    public Builder<?> toBuilder() {
        return Authentication.super.toBuilder();
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        throw new IllegalArgumentException("Já estamos autenticados!");
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public @Nullable Object getPrincipal() {
        return this.identificacaoUsuario;
    }

    @Override
    public @Nullable Object getDetails() {
        return null;
    }
}
