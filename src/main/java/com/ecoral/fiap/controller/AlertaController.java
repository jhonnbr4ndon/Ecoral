package com.ecoral.fiap.controller;

import com.ecoral.fiap.entities.Alerta;
import com.ecoral.fiap.entities.dto.AlertaDTO;
import com.ecoral.fiap.services.AlertaService;
import com.ecoral.fiap.services.Exceptions.ResourceNotFoundException;
import com.ecoral.fiap.services.mapper.AlertaMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alerta")
public class AlertaController {

    @Autowired
    private AlertaService alertaService;

    @GetMapping()
    public ResponseEntity<List<AlertaDTO>> listaAlerta() {
        List<AlertaDTO> alertaDTOS = alertaService.listaAlerta();
        return ResponseEntity.ok(alertaDTOS);
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
}
