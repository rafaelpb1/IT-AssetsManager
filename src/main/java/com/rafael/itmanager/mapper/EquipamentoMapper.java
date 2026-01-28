package com.rafael.itmanager.mapper;

import com.rafael.itmanager.dto.EquipamentoDTOs.EquipamentoRequestDTO;
import com.rafael.itmanager.dto.EquipamentoDTOs.EquipamentoResponseDTO;
import com.rafael.itmanager.model.Equipamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EquipamentoMapper {

    Equipamento toEntity(EquipamentoRequestDTO dto);

    EquipamentoResponseDTO toDTO(Equipamento entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    void updateFromDTO(EquipamentoRequestDTO dto,
                       @MappingTarget Equipamento entity);
}
