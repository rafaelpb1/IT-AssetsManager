package com.rafael.itmanager.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "EMPRESTIMO")
@Data
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipamento_id")
    private Equipamento equipamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "colaborador_id")
    private Colaborador colaborador;

    @Column(name = "data_emprestimo")
    private LocalDate dataEmprestimo;
    @Column(name = "data_devolucao")
    private LocalDate dataDevolucao;

    @Column
    private String observacao;
}
