package com.rafael.itmanager.dto;

import jakarta.validation.constraints.NotBlank;

public record UsuarioRequestDTO(
        @NotBlank(message = "Campo obrigatório")
        String login,
        @NotBlank(message = "Campo obrigatório")
        String senha
) {
}
