package com.rafael.itmanager.security;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class IdentificacaoUsuario {
    private String id;
    private String login;
    private String senha;
    private List<String> permissoes;

    public IdentificacaoUsuario(String id, String login, String senha, List<String> permissoes) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.permissoes = permissoes;
    }

    public List<String> getPermissoes() {
        if (permissoes == null) {
            permissoes = new ArrayList<>();
        }
        return permissoes;
    }
}
