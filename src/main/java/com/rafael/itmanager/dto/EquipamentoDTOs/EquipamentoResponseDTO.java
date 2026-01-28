package com.rafael.itmanager.dto.EquipamentoDTOs;

import com.rafael.itmanager.model.Equipamento;
import com.rafael.itmanager.model.StatusEquipamento;
import com.rafael.itmanager.model.TipoEquipamento;

public record EquipamentoResponseDTO(
        Long id,
        String nome,
        String patrimonio,
        TipoEquipamento tipo,
        StatusEquipamento status
) {
    public EquipamentoResponseDTO(Equipamento entity) {
        this(
                entity.getId(),
                entity.getNome(),
                entity.getPatrimonio(),
                entity.getTipo(),
                entity.getStatus()
        );
    }
}
