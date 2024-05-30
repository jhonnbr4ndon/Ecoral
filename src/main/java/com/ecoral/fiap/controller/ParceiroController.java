package com.ecoral.fiap.controller;

import com.ecoral.fiap.entities.Parceiro;
import com.ecoral.fiap.entities.dto.ParceiroDTO;
import com.ecoral.fiap.services.Exceptions.ResourceNotFoundException;
import com.ecoral.fiap.services.ParceiroService;
import com.ecoral.fiap.services.mapper.ParceiroMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parceiro")
public class ParceiroController {

    @Autowired
    private ParceiroService parceiroService;

    @GetMapping()
    public ResponseEntity<List<ParceiroDTO>> listaParceiro() {
        List<ParceiroDTO> parceiroDTOS = parceiroService.listaParceiro();
        return ResponseEntity.ok(parceiroDTOS);
    }

    @PostMapping()
    public ResponseEntity<ParceiroDTO> criarParceiro(@Valid @RequestBody ParceiroDTO parceiroDTO) {
        Parceiro parceiro = ParceiroMapper.toEntity(parceiroDTO);
        ParceiroDTO novoParceiro = parceiroService.criarParceiro(parceiro);
        return ResponseEntity.ok(novoParceiro);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> encontrarParceiroPorID(@PathVariable Long id) {
        try {
            ParceiroDTO parceiroDTO = parceiroService.encontrarParceiroPorId(id);
            return ResponseEntity.ok(parceiroDTO);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Parceiro não encontrado com o ID: " + id);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarParceiro(@PathVariable Long id, @Valid @RequestBody ParceiroDTO parceiroDTO) {
        try {
            ParceiroDTO parceiroAtualizado = parceiroService.atualizarParceiro(id, parceiroDTO);
            return ResponseEntity.ok(parceiroAtualizado);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Parceiro não encontrado com o ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarParceiro(@PathVariable Long id) {
        try {
            parceiroService.deletarParceiro(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Parceiro não encontrado com o ID: " + id);
        }
    }
}
