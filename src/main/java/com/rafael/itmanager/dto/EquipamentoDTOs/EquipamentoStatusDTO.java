package com.rafael.itmanager.dto.EquipamentoDTOs;

import com.rafael.itmanager.model.StatusEquipamento;
import jakarta.validation.constraints.NotNull;

public record EquipamentoStatusDTO(
        @NotNull(message = "O novo status deve ser informado")
        StatusEquipamento status
) {
}
