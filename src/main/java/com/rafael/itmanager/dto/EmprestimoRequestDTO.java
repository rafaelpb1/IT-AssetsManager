package com.rafael.itmanager.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EmprestimoRequestDTO(
        @NotNull(message = "ID do colaborador é obrigatório")
        Long colaboradorId,
        @NotNull(message = "ID do equipamento é obrigatório")
        Long equipamentoId,
        @FutureOrPresent(message = "A data de devolução não pode ser no passado")
        @NotNull(message = "A data de devolução é obrigatória")
        LocalDate dataDevolucao,
        String observacao
) {
}
