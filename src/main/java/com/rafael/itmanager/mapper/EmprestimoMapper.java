package com.rafael.itmanager.mapper;

import com.rafael.itmanager.dto.EmprestimoDTOs.EmprestimoRequestDTO;
import com.rafael.itmanager.dto.EmprestimoDTOs.EmprestimoResponseDTO;
import com.rafael.itmanager.model.Emprestimo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EmprestimoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "colaborador", ignore = true)
    @Mapping(target = "equipamento", ignore = true)
    Emprestimo toEntity(EmprestimoRequestDTO dto);

    EmprestimoResponseDTO toDTO(Emprestimo entity);

    void updateFromDTO(EmprestimoRequestDTO dto,
                             @MappingTarget Emprestimo entity);
}
