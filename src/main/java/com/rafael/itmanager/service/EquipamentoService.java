package com.rafael.itmanager.service;

import com.rafael.itmanager.dto.EquipamentoRequestDTO;
import com.rafael.itmanager.dto.EquipamentoResponseDTO;
import com.rafael.itmanager.model.Equipamento;
import com.rafael.itmanager.repository.EquipamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipamentoService {

    private final EquipamentoRepository repository;

    @Transactional
    public EquipamentoResponseDTO salvarEquipamento(EquipamentoRequestDTO dto) {
        if(dto.patrimonio() != null && repository.existsByPatrimonio(dto.patrimonio())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Equipamento com patrimonio já registrado");
        }

        Equipamento equipamento = new Equipamento();
        equipamento.setNome(dto.nome());
        equipamento.setPatrimonio(dto.patrimonio());
        equipamento.setTipo(dto.tipo());
        equipamento.setStatus(dto.status());

        Equipamento salvo = repository.save(equipamento);

        return new EquipamentoResponseDTO(salvo);
    }

    public List<EquipamentoResponseDTO> listarEquipamentos() {
        List<Equipamento> equipamentos = repository.findAll();
        return equipamentos.stream()
                .map(EquipamentoResponseDTO::new)
                .toList();
    }

    public EquipamentoResponseDTO buscarEquipamentoPorId(Long id) {
        var equipamento = repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Equipamento não encontrado"));

        return new EquipamentoResponseDTO(equipamento);
    }

    @Transactional
    public EquipamentoResponseDTO atualizarEquipamento(Long id, EquipamentoRequestDTO dto) {
        var equipamento = repository
                .findById(id)
                .orElseThrow( () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Equipamento não encontrado"));

        if (!equipamento.getPatrimonio().equals(dto.patrimonio()) &&
                repository.existsByPatrimonio(dto.patrimonio())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Patrimônio já em uso por outro equipamento");
        }

        equipamento.setNome(dto.nome());
        equipamento.setPatrimonio(dto.patrimonio());
        equipamento.setTipo(dto.tipo());
        equipamento.setStatus(dto.status());

        return new  EquipamentoResponseDTO(equipamento);
    }

    @Transactional
    public void excluirEquipamento(Long id) {
        var equipamento = repository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Equipamento não encontrado"));

        repository.delete(equipamento);
    }
}
