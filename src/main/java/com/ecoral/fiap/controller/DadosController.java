package com.ecoral.fiap.controller;

import com.ecoral.fiap.entities.Dados;
import com.ecoral.fiap.entities.dto.DadosDTO;
import com.ecoral.fiap.services.DadosService;
import com.ecoral.fiap.services.Exceptions.ResourceNotFoundException;
import com.ecoral.fiap.services.mapper.DadosMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dados")
public class DadosController {

    @Autowired
    private DadosService dadosService;

    @GetMapping()
    public ResponseEntity<List<DadosDTO>> listaDados() {
        List<DadosDTO> dadosDTOS = dadosService.listaDados();
        return ResponseEntity.ok(dadosDTOS);
    }

    @PostMapping()
    public ResponseEntity<DadosDTO> criarDado(@Valid @RequestBody DadosDTO dadosDTO) {
        Dados dados = DadosMapper.toEntity(dadosDTO);
        DadosDTO novoDado = dadosService.criarDados(dados);
        return ResponseEntity.ok(novoDado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> encontrarDadoPorID(@PathVariable Long id) {
        try {
            DadosDTO dadosDTO = dadosService.encontrarDadosPorId(id);
            return ResponseEntity.ok(dadosDTO);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Dado não encontrado com o ID: " + id);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarDado(@PathVariable Long id, @Valid @RequestBody DadosDTO dadosDTO) {
        try {
            DadosDTO dadoAtualizado = dadosService.atualizarDado(id, dadosDTO);
            return ResponseEntity.ok(dadoAtualizado);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Dado não encontrado com o ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarDado(@PathVariable Long id) {
        try {
            dadosService.deletarDado(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Dado não encontrado com o ID: " + id);
        }
    }
}
