package com.rafael.itmanager.service;

import com.rafael.itmanager.dto.EquipamentoRequestDTO;
import com.rafael.itmanager.dto.EquipamentoResponseDTO;
import com.rafael.itmanager.model.Equipamento;
import com.rafael.itmanager.repository.EquipamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipamentoService {

    private final EquipamentoRepository repository;

    public EquipamentoResponseDTO salvarEquipamento(EquipamentoRequestDTO dto) {
        if(dto.patrimonio() != null && repository.existsByPatrimonio(dto.patrimonio())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Equipamento com patrimonio já registrado");
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
        List<EquipamentoResponseDTO> lista = equipamentos.stream()
                .map(EquipamentoResponseDTO::new)
                .toList();

        if (equipamentos.isEmpty()) throw new ResponseStatusException(HttpStatus.OK, "Nenhum equipamento encontrado");
        return lista;
    }

    public EquipamentoResponseDTO buscarEquipamentoPorId(Long id) {
        var equipamento = repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipamento não encontrado"));

        return new EquipamentoResponseDTO(equipamento);
    }
}
