package com.rafael.itmanager.dto.UsuarioDTOs;

import java.util.List;

public record UsuarioResponseDTO(
        String id,
        String login,
        Boolean admin,
        List<String> permissoes
) {
}
