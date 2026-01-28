package com.rafael.itmanager.mapper;

import com.rafael.itmanager.dto.UsuarioDTOs.UsuarioRequestDTO;
import com.rafael.itmanager.dto.UsuarioDTOs.UsuarioResponseDTO;
import com.rafael.itmanager.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioRequestDTO dto);

    UsuarioResponseDTO toDTO(Usuario entity);
}
