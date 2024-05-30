package com.ecoral.fiap.controller;

import com.ecoral.fiap.entities.Manutencao;
import com.ecoral.fiap.entities.dto.ManutencaoDTO;
import com.ecoral.fiap.services.Exceptions.ResourceNotFoundException;
import com.ecoral.fiap.services.ManutencaoService;
import com.ecoral.fiap.services.mapper.ManutencaoMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manutencao")
public class ManutencaoController {

    @Autowired
    private ManutencaoService manutencaoService;

    @GetMapping()
    public ResponseEntity<List<ManutencaoDTO>> listaManutencao() {
        List<ManutencaoDTO> manutencaoDTOS = manutencaoService.listaManutencao();
        return ResponseEntity.ok(manutencaoDTOS);
    }

    @PostMapping()
    public ResponseEntity<ManutencaoDTO> criarManutencao(@Valid @RequestBody ManutencaoDTO manutencaoDTO) {
        Manutencao manutencao = ManutencaoMapper.toEntity(manutencaoDTO);
        ManutencaoDTO novoManutencao = manutencaoService.criarManutencao(manutencao);
        return ResponseEntity.ok(novoManutencao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> encontrarManutencaoPorID(@PathVariable Long id) {
        try {
            ManutencaoDTO manutencaoDTO = manutencaoService.encontrarManutencaoPorId(id);
            return ResponseEntity.ok(manutencaoDTO);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Manutenção não encontrado com o ID: " + id);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarManutencao(@PathVariable Long id, @Valid @RequestBody ManutencaoDTO manutencaoDTO) {
        try {
            ManutencaoDTO manutencaoAtualizado = manutencaoService.atualizarManutencao(id, manutencaoDTO);
            return ResponseEntity.ok(manutencaoAtualizado);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Manutenção não encontrado com o ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarManutencao(@PathVariable Long id) {
        try {
            manutencaoService.deletarManutencao(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Manutenção não encontrado com o ID: " + id);
        }
    }
}
