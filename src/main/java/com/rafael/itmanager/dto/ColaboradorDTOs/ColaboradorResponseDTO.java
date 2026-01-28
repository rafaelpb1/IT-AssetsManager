package com.rafael.itmanager.dto.ColaboradorDTOs;

import com.rafael.itmanager.model.Colaborador;

public record ColaboradorResponseDTO(
        Long id,
        String nome,
        String cpf,
        String setor
) {
    public ColaboradorResponseDTO(Colaborador entity) {
        this(
                entity.getId(),
                entity.getNome(),
                entity.getCpf(),
                entity.getSetor()
        );
    }
}
