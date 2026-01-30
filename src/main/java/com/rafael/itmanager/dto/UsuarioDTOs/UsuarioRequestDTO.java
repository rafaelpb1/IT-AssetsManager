package com.rafael.itmanager.dto.UsuarioDTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UsuarioRequestDTO(
        @NotBlank(message = "Campo obrigatório")
        String login,
        @NotBlank(message = "Campo obrigatório")
        String senha,
        @NotBlank(message = "Campo obrigatório")
        List<String> permissoes
) {
}
