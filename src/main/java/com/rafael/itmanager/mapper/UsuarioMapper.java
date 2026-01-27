package com.rafael.itmanager.mapper;

import com.rafael.itmanager.dto.UsuarioRequestDTO;
import com.rafael.itmanager.dto.UsuarioResponseDTO;
import com.rafael.itmanager.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioRequestDTO dto);

    UsuarioResponseDTO toDTO(Usuario entity);
}
