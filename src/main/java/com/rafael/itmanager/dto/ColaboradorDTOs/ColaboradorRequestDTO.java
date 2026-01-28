package com.rafael.itmanager.dto.ColaboradorDTOs;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record ColaboradorRequestDTO(
        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @CPF(message = "CPF inválido")
        String cpf,

        @NotBlank(message = "O setor é obrigatório")
        String setor
) {
}
