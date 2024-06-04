package com.ecoral.fiap.controller;

import com.ecoral.fiap.entities.Funcionario;
import com.ecoral.fiap.entities.dto.FuncionarioDTO;
import com.ecoral.fiap.services.Exceptions.ResourceNotFoundException;
import com.ecoral.fiap.services.FuncionarioService;
import com.ecoral.fiap.services.mapper.FuncionarioMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping()
    public ResponseEntity<List<FuncionarioDTO>> listaFuncionario() {
        List<FuncionarioDTO> funcionarioDTOS = funcionarioService.listaFuncionario();
        return ResponseEntity.ok(funcionarioDTOS);
    }

    @PostMapping()
    public ResponseEntity<FuncionarioDTO> criarFuncionario(@Valid @RequestBody FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario = FuncionarioMapper.toEntity(funcionarioDTO);
        FuncionarioDTO novoFuncionario = funcionarioService.criarFuncionario(funcionario);
        return ResponseEntity.ok(novoFuncionario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> encontrarFuncionarioPorID(@PathVariable Long id) {
        try {
            FuncionarioDTO funcionarioDTO = funcionarioService.encontrarFuncionarioPorId(id);
            return ResponseEntity.ok(funcionarioDTO);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Funcionario não encontrado com o ID: " + id);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizaFuncionario(@PathVariable Long id, @Valid @RequestBody FuncionarioDTO funcionarioDTO) {
        try {
            FuncionarioDTO funcionarioAtualizado = funcionarioService.atualizarFuncionario(id, funcionarioDTO);
            return ResponseEntity.ok(funcionarioAtualizado);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Funcionario não encontrado com o ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarFuncionario(@PathVariable Long id) {
        try {
            funcionarioService.deletarFuncionario(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Funcionario não encontrado com o ID: " + id);
        }
    }
}
