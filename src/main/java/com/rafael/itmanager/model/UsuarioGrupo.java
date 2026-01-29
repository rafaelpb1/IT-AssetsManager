package com.rafael.itmanager.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UsuarioGrupo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "id_grupo")
    private Grupo grupo;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public UsuarioGrupo(Usuario usuario, Grupo grupo) {
        this.usuario = usuario;
        this.grupo = grupo;

    }
}
