package com.rafael.itmanager.config;

import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomAuthentication implements AuthenticationProvider {
    @Override
    public @Nullable Authentication authenticate(Authentication authentication) throws AuthenticationException {

        var login = authentication.getName();
        var senha = authentication.getCredentials().toString();

        String loginMain = "master";
        String senhaMain = "master";

        if (loginMain.equals(login) && senhaMain.equals(senha)) {
            return new UsernamePasswordAuthenticationToken("Sou master", null, List.of(new SimpleGrantedAuthority("ADMIN")));
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
