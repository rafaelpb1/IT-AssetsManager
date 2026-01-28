package com.rafael.itmanager.dto.EquipamentoDTOs;

import com.rafael.itmanager.model.StatusEquipamento;
import com.rafael.itmanager.model.TipoEquipamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EquipamentoRequestDTO(
        @NotBlank(message = "Nome do equipamento é obrigatório")
        String nome,
        @NotNull(message = "Número do patrimonio é obrigátorio")
        String patrimonio,
        @NotNull (message = "O tipo do equipamento é obrigatório")
        TipoEquipamento tipo,
        @NotNull (message = "O status inicial é obrigatório")
        StatusEquipamento status
) {
}
