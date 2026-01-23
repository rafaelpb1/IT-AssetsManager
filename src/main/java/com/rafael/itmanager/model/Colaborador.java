package com.rafael.itmanager.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "COLABORADOR")
@Data
public class Colaborador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, unique = true)
    @CPF
    private String cpf;

    @Column(length = 50)
    private String setor;
}
