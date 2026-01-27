package com.rafael.itmanager.mapper;

import com.rafael.itmanager.dto.ColaboradorRequestDTO;
import com.rafael.itmanager.dto.ColaboradorResponseDTO;
import com.rafael.itmanager.model.Colaborador;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ColaboradorMapper {

    Colaborador toEntity(ColaboradorRequestDTO dto);

    ColaboradorResponseDTO toDTO(Colaborador entity);

    void updateFromDTO(ColaboradorRequestDTO dto,
                       @MappingTarget Colaborador entity);
}
