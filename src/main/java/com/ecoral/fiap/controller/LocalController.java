package com.ecoral.fiap.controller;

import com.ecoral.fiap.entities.Local;
import com.ecoral.fiap.entities.dto.LocalDTO;
import com.ecoral.fiap.services.Exceptions.ResourceNotFoundException;
import com.ecoral.fiap.services.LocalService;
import com.ecoral.fiap.services.mapper.LocalMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/local")
public class LocalController {

    @Autowired
    private LocalService localService;

    @GetMapping()
    public ResponseEntity<List<LocalDTO>> listaLocal() {
        List<LocalDTO> localDTOS = localService.listaLocal();
        return ResponseEntity.ok(localDTOS);
    }

    @PostMapping()
    public ResponseEntity<LocalDTO> criarLocal(@Valid @RequestBody LocalDTO localDTO) {
        Local local = LocalMapper.toEntity(localDTO);
        LocalDTO novoLocal = localService.criarLocal(local);
        return ResponseEntity.ok(novoLocal);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> encontrarLocalPorID(@PathVariable Long id) {
        try {
            LocalDTO localDTO = localService.encontrarLocalPorId(id);
            return ResponseEntity.ok(localDTO);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Local não encontrado com o ID: " + id);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarLocal(@PathVariable Long id, @Valid @RequestBody LocalDTO localDTO) {
        try {
            LocalDTO localAtualizado = localService.atualizarLocal(id, localDTO);
            return ResponseEntity.ok(localAtualizado);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Local não encontrado com o ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarLocal(@PathVariable Long id) {
        try {
            localService.deletarLocal(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Local não encontrado com o ID: " + id);
        }
    }
}
