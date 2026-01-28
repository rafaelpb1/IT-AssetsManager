package com.rafael.itmanager.dto.UsuarioDTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioRequestDTO(
        @NotBlank(message = "Campo obrigatório")
        String login,
        @NotBlank(message = "Campo obrigatório")
        String senha,
        @NotNull(message = "Campo obrigatório")
        Boolean admin
) {
}
