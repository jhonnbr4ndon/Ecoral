package com.ecoral.fiap.controller;

import com.ecoral.fiap.entities.Equipamento;
import com.ecoral.fiap.entities.dto.EquipamentoDTO;
import com.ecoral.fiap.services.EquipamentoService;
import com.ecoral.fiap.services.Exceptions.ResourceNotFoundException;
import com.ecoral.fiap.services.mapper.EquipamentoMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipamento")
public class EquipamentoController {

    @Autowired
    private EquipamentoService equipamentoService;

    @GetMapping()
    public ResponseEntity<List<EquipamentoDTO>> listaEquipamento() {
        List<EquipamentoDTO> equipamentoDTOS = equipamentoService.listaEquipamento();
        return ResponseEntity.ok(equipamentoDTOS);
    }

    @PostMapping()
    public ResponseEntity<EquipamentoDTO> criarEquipamento(@Valid @RequestBody EquipamentoDTO equipamentoDTO) {
        Equipamento equipamento = EquipamentoMapper.toEntity(equipamentoDTO);
        EquipamentoDTO novoEquipamento = equipamentoService.criarEquipamento(equipamento);
        return ResponseEntity.ok(novoEquipamento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> encontrarEquipamentoPorID(@PathVariable Long id) {
        try {
            EquipamentoDTO equipamentoDTO = equipamentoService.encontrarEquipamentoPorId(id);
            return ResponseEntity.ok(equipamentoDTO);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Equipamento não encontrado com o ID: " + id);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarEquipamento(@PathVariable Long id, @Valid @RequestBody EquipamentoDTO equipamentoDTO) {
        try {
            EquipamentoDTO equipamentoAtualizado = equipamentoService.atualizarEquipamento(id, equipamentoDTO);
            return ResponseEntity.ok(equipamentoAtualizado);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Equipamento não encontrado com o ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarEquipamento(@PathVariable Long id) {
        try {
            equipamentoService.deletarEquipamento(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Equipamento não encontrado com o ID: " + id);
        }
    }
}
