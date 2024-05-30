package com.ecoral.fiap.controller;

import com.ecoral.fiap.entities.Relatorio;
import com.ecoral.fiap.entities.dto.RelatorioDTO;
import com.ecoral.fiap.services.Exceptions.ResourceNotFoundException;
import com.ecoral.fiap.services.RelatorioService;
import com.ecoral.fiap.services.mapper.RelatorioMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/relatorio")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @GetMapping()
    public ResponseEntity<List<RelatorioDTO>> listaRelatorio() {
        List<RelatorioDTO> relatorioDTOS = relatorioService.listaRelatorio();
        return ResponseEntity.ok(relatorioDTOS);
    }

    @PostMapping()
    public ResponseEntity<RelatorioDTO> criarRelatorio(@Valid @RequestBody RelatorioDTO relatorioDTO) {
        Relatorio relatorio = RelatorioMapper.toEntity(relatorioDTO);
        RelatorioDTO novoRelatorio = relatorioService.criarRelatorio(relatorio);
        return ResponseEntity.ok(novoRelatorio);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> encontrarRelatorioPorID(@PathVariable Long id) {
        try {
            RelatorioDTO relatorioDTO = relatorioService.encontrarRelatorioPorId(id);
            return ResponseEntity.ok(relatorioDTO);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Relátorio não encontrado com o ID: " + id);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarRelatorio(@PathVariable Long id, @Valid @RequestBody RelatorioDTO relatorioDTO) {
        try {
            RelatorioDTO relatorioAtualizado = relatorioService.atualizarRelatorio(id, relatorioDTO);
            return ResponseEntity.ok(relatorioAtualizado);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Relátorio não encontrado com o ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarRelatorio(@PathVariable Long id) {
        try {
            relatorioService.deletarRelatorio(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Relátorio não encontrado com o ID: " + id);
        }
    }
}
