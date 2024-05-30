package com.ecoral.fiap.controller;

import com.ecoral.fiap.entities.Equipamento;
import com.ecoral.fiap.entities.dto.EquipamentoDTO;
import com.ecoral.fiap.services.EquipamentoService;
import com.ecoral.fiap.services.Exceptions.ResourceNotFoundException;
import com.ecoral.fiap.services.mapper.EquipamentoMapper;
import com.ecoral.fiap.strategies.equipamento.DataAntiga3Strategy;
import com.ecoral.fiap.strategies.equipamento.DataRecente3Strategy;
import com.ecoral.fiap.strategies.equipamento.EquipamentoStrategy;
import com.ecoral.fiap.strategies.equipamento.TipoEquipamentoStrategy;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
@RequestMapping("/equipamento")
public class EquipamentoController {

    @Autowired
    private EquipamentoService equipamentoService;

    @GetMapping("/todos")
    public ResponseEntity<List<EquipamentoDTO>> listaEquipamento() {
        List<EquipamentoDTO> equipamentoDTOS = equipamentoService.listaEquipamento();
        return ResponseEntity.ok(equipamentoDTOS);
    }

    @GetMapping()
    public ResponseEntity<Page<EquipamentoDTO>> listaEquipamentoPaginacao(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<EquipamentoDTO> equipamentosPage = equipamentoService.listaPaginacaoEquipamento(pageable);
        return ResponseEntity.ok(equipamentosPage);
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

    @GetMapping("/nome-ordenado")
    public ResponseEntity<List<EquipamentoDTO>> listaTipoPorNome() {
        EquipamentoStrategy strategy = new TipoEquipamentoStrategy();
        List<EquipamentoDTO> listaTipo = equipamentoService.listaOrganizadaEquipamento(strategy).stream().map(EquipamentoMapper::toDTO).toList();
        return ResponseEntity.ok(listaTipo);
    }

    @GetMapping("/data-recente")
    public ResponseEntity<List<EquipamentoDTO>> listarEquipamentoPorDataRecente() {
        EquipamentoStrategy strategy = new DataRecente3Strategy();
        List<EquipamentoDTO> listarData = equipamentoService.listaOrganizadaEquipamento(strategy).stream().map(EquipamentoMapper::toDTO).toList();
        return ResponseEntity.ok(listarData);
    }

    @GetMapping("/data-antiga")
    public ResponseEntity<List<EquipamentoDTO>> listarEquipamentoPorDataAntiga() {
        EquipamentoStrategy strategy = new DataAntiga3Strategy();
        List<EquipamentoDTO> listarData = equipamentoService.listaOrganizadaEquipamento(strategy).stream().map(EquipamentoMapper::toDTO).toList();
        return ResponseEntity.ok(listarData);
    }
}
