package com.rafael.itmanager.dto;

public record UsuarioResponseDTO(
        Long id,
        String login,
        String senha
) {
}
