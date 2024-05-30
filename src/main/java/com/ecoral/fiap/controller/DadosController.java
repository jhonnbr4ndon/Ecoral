package com.ecoral.fiap.controller;

import com.ecoral.fiap.entities.Dados;
import com.ecoral.fiap.entities.dto.DadosDTO;
import com.ecoral.fiap.services.DadosService;
import com.ecoral.fiap.services.Exceptions.ResourceNotFoundException;
import com.ecoral.fiap.services.mapper.DadosMapper;
import com.ecoral.fiap.strategies.dados.DadosStrategy;
import com.ecoral.fiap.strategies.dados.DataAntiga4Strategy;
import com.ecoral.fiap.strategies.dados.DataRecente4Strategy;
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
@RequestMapping("/dados")
public class DadosController {

    @Autowired
    private DadosService dadosService;

    @GetMapping("/todos")
    public ResponseEntity<List<DadosDTO>> listaDados() {
        List<DadosDTO> dadosDTOS = dadosService.listaDados();
        return ResponseEntity.ok(dadosDTOS);
    }

    @GetMapping()
    public ResponseEntity<Page<DadosDTO>> listaDadosPaginacao(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<DadosDTO> dadosPage = dadosService.listaPaginacaoDados(pageable);
        return ResponseEntity.ok(dadosPage);
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

    @GetMapping("/data-recente")
    public ResponseEntity<List<DadosDTO>> listaDadoPorDataRecente() {
        DadosStrategy strategy = new DataRecente4Strategy();
        List<DadosDTO> listaData = dadosService.listaOrganizadaDados(strategy).stream().map(DadosMapper::toDTO).toList();
        return ResponseEntity.ok(listaData);
    }

    @GetMapping("/data-antiga")
    public ResponseEntity<List<DadosDTO>> listaDadoPorDataAntiga() {
        DadosStrategy strategy = new DataAntiga4Strategy();
        List<DadosDTO> listaData = dadosService.listaOrganizadaDados(strategy).stream().map(DadosMapper::toDTO).toList();
        return ResponseEntity.ok(listaData);
    }
}
