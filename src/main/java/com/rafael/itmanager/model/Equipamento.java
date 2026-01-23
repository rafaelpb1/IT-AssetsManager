package com.rafael.itmanager.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "EQUIPAMENTO")
@Data
public class Equipamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String patrimonio;

    @Enumerated(EnumType.STRING)
    private TipoEquipamento tipo;

    @Enumerated(EnumType.STRING)
    private StatusEquipamento status;
}
