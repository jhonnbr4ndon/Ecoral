package com.ecoral.fiap.services;

import com.ecoral.fiap.entities.Alerta;
import com.ecoral.fiap.entities.dto.AlertaDTO;
import com.ecoral.fiap.repositories.AlertaRepository;
import com.ecoral.fiap.services.Exceptions.ResourceNotFoundException;
import com.ecoral.fiap.services.mapper.AlertaMapper;
import com.ecoral.fiap.strategies.alerta.AlertaStrategy;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlertaService {

    @Autowired
    private AlertaRepository alertaRepository;

    @Autowired
    private AlertaStrategy alertaStrategy;

    @Autowired
    private void setAlertaStrategy(AlertaStrategy alertaStrategy) {
        this.alertaStrategy = alertaStrategy;
    }

    public List<Alerta> listaOrganizadaAlerta(AlertaStrategy alertaStrategy) {
        List<Alerta> alertas = alertaRepository.findAll();
        return alertaStrategy.organizar(alertas);
    }

    @Transactional
    public AlertaDTO criarAlerta(Alerta alerta) {
        alerta = alertaRepository.save(alerta);
        return AlertaMapper.toDTO(alerta);
    }

    public List<AlertaDTO> listaAlerta() {
        List<Alerta> alertas = alertaRepository.findAll();
        return alertas.stream()
                .map(AlertaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Page<AlertaDTO> listaPaginacaoAlerta(Pageable pageable) {
        return alertaRepository.findAll(pageable).map(AlertaMapper::toDTO);
    }

    public AlertaDTO encontrarAlertaPorId(Long id) {
        Optional<Alerta> optionalAlerta = alertaRepository.findById(id);
        if (optionalAlerta.isPresent()) {
            Alerta alerta = optionalAlerta.get();
            return AlertaMapper.toDTO(alerta);
        } else {
            throw new ResourceNotFoundException("Alerta não encontrado com o ID: " + id);
        }
    }

    @Transactional
    public AlertaDTO atualizarAlerta(Long id, AlertaDTO alertaDTO) {
        Optional<Alerta> optionalAlerta = alertaRepository.findById(id);
        if (optionalAlerta.isPresent()) {
            Alerta alerta = optionalAlerta.get();
            alerta.setData(alertaDTO.getData());
            alerta.setNivel(alertaDTO.getNivel());
            alerta.setDescricao(alertaDTO.getDescricao());
            alerta.setStatus(alertaDTO.getStatus());
            return AlertaMapper.toDTO(alerta);
        } else {
            throw new ResourceNotFoundException("Alerta não encontrado com o ID: " + id);
        }
    }

    @Transactional
    public void deletarAlerta(Long id) {
        Optional<Alerta> optionalAlerta = alertaRepository.findById(id);
        if (optionalAlerta.isPresent()) {
            alertaRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Alerta não encontrado com o ID: " + id);
        }
    }
}
