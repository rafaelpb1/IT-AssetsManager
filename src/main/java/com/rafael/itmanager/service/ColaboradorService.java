package com.rafael.itmanager.service;

import com.rafael.itmanager.dto.ColaboradorDTOs.ColaboradorRequestDTO;
import com.rafael.itmanager.dto.ColaboradorDTOs.ColaboradorResponseDTO;
import com.rafael.itmanager.mapper.ColaboradorMapper;
import com.rafael.itmanager.model.Colaborador;
import com.rafael.itmanager.repository.ColaboradorRepository;
import com.rafael.itmanager.repository.EmprestimoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ColaboradorService {

    private final ColaboradorRepository repository;
    private final EmprestimoRepository  emprestimoRepository;
    private final ColaboradorMapper mapper;

    @Transactional
    public ColaboradorResponseDTO salvarColaborador(ColaboradorRequestDTO dto) {
        if (dto.cpf() != null && repository.existsByCpf(dto.cpf())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Já existe um colaborador com esse CPF");
        }

        var colaborador = mapper.toEntity(dto);
        Colaborador salvo = repository.save(colaborador);
        return mapper.toDTO(salvo);

    }

    public List<ColaboradorResponseDTO> listarColaboradores() {
        List<Colaborador> colaboradores = repository.findAll();
        List<ColaboradorResponseDTO> lista = colaboradores.stream()
                .map(mapper::toDTO)
                .toList();

        if (colaboradores.isEmpty()) throw new ResponseStatusException(
                HttpStatus.NO_CONTENT, "Nenhum colaborador encontrado");
        return lista;
    }

    public ColaboradorResponseDTO buscarColaboradorPorId(Long id) {
        var colaborador = repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Colaborador não encontrado"));

        return mapper.toDTO(colaborador);
    }

    @Transactional
    public void excluirColaborador(Long id) {
        var colaborador = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Colaborador não encontrado"));

        if (emprestimoRepository.existsByColaboradorId(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Não é possível deletar um colaborador que possui empréstimos registrados.");
        }

        repository.delete(colaborador);
    }

    @Transactional
    public ColaboradorResponseDTO atualizarColaborador(Long id, ColaboradorRequestDTO dto) {
        var colaborador = repository.findById(id)
                .orElseThrow( () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Colaborador não encontrado"));

        mapper.updateFromDTO(dto, colaborador);
        return mapper.toDTO(colaborador);
    }
}
