package com.rafael.itmanager.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "usuario")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    private String login;

    @Column
    private String senha;

    @Column(name = "is_admin", nullable = false)
    private Boolean admin;
}
