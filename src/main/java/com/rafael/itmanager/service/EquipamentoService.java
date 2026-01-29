package com.rafael.itmanager.service;

import com.rafael.itmanager.dto.EquipamentoDTOs.EquipamentoRequestDTO;
import com.rafael.itmanager.dto.EquipamentoDTOs.EquipamentoResponseDTO;
import com.rafael.itmanager.dto.EquipamentoDTOs.EquipamentoStatusDTO;
import com.rafael.itmanager.mapper.EquipamentoMapper;
import com.rafael.itmanager.model.Equipamento;
import com.rafael.itmanager.model.StatusEquipamento;
import com.rafael.itmanager.repository.EquipamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipamentoService {

    private final EquipamentoRepository repository;
    private final EquipamentoMapper mapper;

    @Transactional
    public EquipamentoResponseDTO salvarEquipamento(EquipamentoRequestDTO dto) {
        if(dto.patrimonio() != null && repository.existsByPatrimonio(dto.patrimonio())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Equipamento com patrimonio já registrado");
        }

        var equipamento = mapper.toEntity(dto);
        Equipamento salvo = repository.save(equipamento);
        return mapper.toDTO(salvo);
    }

    public List<EquipamentoResponseDTO> listarEquipamentos() {
        List<Equipamento> equipamentos = repository.findAll();
        return equipamentos.stream()
                .map(mapper::toDTO)
                .toList();
    }

    public EquipamentoResponseDTO buscarEquipamentoPorId(Long id) {
        var equipamento = repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Equipamento não encontrado"));

        return mapper.toDTO(equipamento);
    }

    @Transactional
    public EquipamentoResponseDTO atualizarEquipamento(Long id, EquipamentoRequestDTO dto) {
        var equipamento = repository
                .findById(id)
                .orElseThrow( () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Equipamento não encontrado"));

        if (dto.patrimonio() != null &&
                !dto.patrimonio().equals(equipamento.getPatrimonio()) &&
                repository.existsByPatrimonio(dto.patrimonio())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Patrimônio já em uso por outro equipamento");
        }

        mapper.updateFromDTO(dto, equipamento);
        Equipamento atualizado = repository.save(equipamento);
        return mapper.toDTO(atualizado);
    }

    @Transactional
    public EquipamentoResponseDTO atualizarStatus(Long id, EquipamentoStatusDTO dto) {
        var equipamento  = repository.
                findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipamento não encontrado"));

        if (equipamento.getStatus() == StatusEquipamento.EMPRESTADO && dto.status() == StatusEquipamento.DISPONIVEL) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Para disponibilizar este equipamento, realize o processo de devolução formal.");
        }

        equipamento.setStatus(dto.status());
        return mapper.toDTO(equipamento);
    }

    @Transactional
    public void excluirEquipamento(Long id) {
        var equipamento = repository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Equipamento não encontrado"));

        repository.delete(equipamento);
    }
}
