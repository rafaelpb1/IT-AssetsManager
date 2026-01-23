package com.rafael.itmanager.dto;

import com.rafael.itmanager.model.Emprestimo;

import java.time.LocalDate;

public record EmprestimoResponseDTO(
        Long id,
        String nomeEquipamento,
        String patrimonio,
        String nomeColaborador,
        LocalDate dataDevolucao
) {
    public EmprestimoResponseDTO(Emprestimo entity) {
        this(
                entity.getId(),
                entity.getEquipamento().getNome(),
                entity.getEquipamento().getPatrimonio(),
                entity.getColaborador().getNome(),
                entity.getDataDevolucao()
        );
    }
}
