package com.ecoral.fiap.controller;

import com.ecoral.fiap.entities.Alerta;
import com.ecoral.fiap.entities.dto.AlertaDTO;
import com.ecoral.fiap.services.AlertaService;
import com.ecoral.fiap.services.Exceptions.ResourceNotFoundException;
import com.ecoral.fiap.services.mapper.AlertaMapper;
import com.ecoral.fiap.strategies.alerta.AlertaStrategy;
import com.ecoral.fiap.strategies.alerta.DataAntiga5Strategy;
import com.ecoral.fiap.strategies.alerta.DataRecente5Strategy;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
@RequestMapping("/alerta")
public class AlertaController {

    @Autowired
    private AlertaService alertaService;

    @GetMapping("/todos")
    public ResponseEntity<List<AlertaDTO>> listaAlerta() {
        List<AlertaDTO> alertaDTOS = alertaService.listaAlerta();
        return ResponseEntity.ok(alertaDTOS);
    }

    @GetMapping()
    public ResponseEntity<Page<AlertaDTO>> listaAlertaPaginacao(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<AlertaDTO> alertaPage = alertaService.listaPaginacaoAlerta(pageable);
        return ResponseEntity.ok(alertaPage);
    }

    @PostMapping()
    public ResponseEntity<AlertaDTO> criarAlerta(@Valid @RequestBody AlertaDTO alertaDTO) {
        Alerta alerta = AlertaMapper.toEntity(alertaDTO);
        AlertaDTO novoAlerta = alertaService.criarAlerta(alerta);
        return ResponseEntity.ok(novoAlerta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> encontrarAlertaPorId(@PathVariable Long id) {
        try {
            AlertaDTO alertaDTO = alertaService.encontrarAlertaPorId(id);
            return ResponseEntity.ok(alertaDTO);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Alerta não encontrado com o ID: " + id);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarAlerta(@PathVariable Long id, @Valid @RequestBody AlertaDTO alertaDTO) {
        try {
            AlertaDTO alertaAtualizado = alertaService.atualizarAlerta(id, alertaDTO);
            return ResponseEntity.ok(alertaAtualizado);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Alerta não encontrado com o ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarAlerta(@PathVariable Long id) {
        try {
            alertaService.deletarAlerta(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Alerta não encontrado com o ID: " + id);
        }
    }

    @GetMapping("/data-recente")
    public ResponseEntity<List<AlertaDTO>> listaAlertaPorDataRecente() {
        AlertaStrategy strategy = new DataRecente5Strategy();
        List<AlertaDTO> listaData = alertaService.listaOrganizadaAlerta(strategy).stream().map(AlertaMapper::toDTO).toList();
        return ResponseEntity.ok(listaData);
    }

    @GetMapping("/data-antiga")
    public ResponseEntity<List<AlertaDTO>> listaAlertaPorDataAntiga() {
        AlertaStrategy strategy = new DataAntiga5Strategy();
        List<AlertaDTO> listaData = alertaService.listaOrganizadaAlerta(strategy).stream().map(AlertaMapper::toDTO).toList();
        return ResponseEntity.ok(listaData);
    }

}
