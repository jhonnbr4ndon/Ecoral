package com.ecoral.fiap.services;

import com.ecoral.fiap.entities.Equipamento;
import com.ecoral.fiap.entities.dto.EquipamentoDTO;
import com.ecoral.fiap.repositories.EquipamentoRepository;
import com.ecoral.fiap.services.Exceptions.ResourceNotFoundException;
import com.ecoral.fiap.services.mapper.EquipamentoMapper;
import com.ecoral.fiap.strategies.equipamento.EquipamentoStrategy;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EquipamentoService {

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    @Autowired
    private EquipamentoStrategy equipamentoStrategy;

    @Autowired
    private void setEquipamentoStrategy(EquipamentoStrategy equipamentoStrategy) {
        this.equipamentoStrategy = equipamentoStrategy;
    }

    public List<Equipamento> listaOrganizadaEquipamento(EquipamentoStrategy equipamentoStrategy) {
        List<Equipamento> equipamentos = equipamentoRepository.findAll();
        return equipamentoStrategy.organizar(equipamentos);
    }

    @Transactional
    public EquipamentoDTO criarEquipamento(Equipamento equipamento) {
        equipamento = equipamentoRepository.save(equipamento);
        return EquipamentoMapper.toDTO(equipamento);
    }

    public List<EquipamentoDTO> listaEquipamento() {
        List<Equipamento> equipamentos = equipamentoRepository.findAll();
        return equipamentos.stream()
                .map(EquipamentoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Page<EquipamentoDTO> listaPaginacaoEquipamento(Pageable pageable) {
        return equipamentoRepository.findAll(pageable).map(EquipamentoMapper::toDTO);
    }

    public EquipamentoDTO encontrarEquipamentoPorId(Long id) {
        Optional<Equipamento> optionalEquipamento = equipamentoRepository.findById(id);
        if (optionalEquipamento.isPresent()) {
            Equipamento equipamento = optionalEquipamento.get();
            return EquipamentoMapper.toDTO(equipamento);
        } else {
            throw new ResourceNotFoundException("Equipamento não encontrado com o ID: " + id);
        }
    }

    @Transactional
    public EquipamentoDTO atualizarEquipamento(Long id, EquipamentoDTO equipamentoDTO) {
        Optional<Equipamento> optionalEquipamento = equipamentoRepository.findById(id);
        if (optionalEquipamento.isPresent()) {
            Equipamento equipamento = optionalEquipamento.get();
            equipamento.setTipo(equipamentoDTO.getTipo());
            equipamento.setData(equipamentoDTO.getData());
            equipamento.setStatus(equipamentoDTO.getStatus());
            return EquipamentoMapper.toDTO(equipamento);
        } else {
            throw new ResourceNotFoundException("Equipamento não encontrado com o ID: " + id);
        }
    }

    @Transactional
    public void deletarEquipamento(Long id) {
        Optional<Equipamento> optionalEquipamento = equipamentoRepository.findById(id);
        if (optionalEquipamento.isPresent()) {
            equipamentoRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Equipamento não encontrado com o ID: " + id);
        }
    }
}
