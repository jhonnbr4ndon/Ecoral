package com.ecoral.fiap.controller;

import com.ecoral.fiap.entities.Relatorio;
import com.ecoral.fiap.entities.dto.RelatorioDTO;
import com.ecoral.fiap.services.Exceptions.ResourceNotFoundException;
import com.ecoral.fiap.services.RelatorioService;
import com.ecoral.fiap.services.mapper.RelatorioMapper;
import com.ecoral.fiap.strategies.relatorio.DataAntigaStrategy;
import com.ecoral.fiap.strategies.relatorio.DataRecenteStrategy;
import com.ecoral.fiap.strategies.relatorio.NomeResponsavelStrategy;
import com.ecoral.fiap.strategies.relatorio.RelatorioStrategy;
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
@RequestMapping("/relatorio")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @GetMapping("/todos")
    public ResponseEntity<List<RelatorioDTO>> listarTodosRelatorios() {
        List<RelatorioDTO> todosRelatorios = relatorioService.listaTodosRelatorios();
        return ResponseEntity.ok(todosRelatorios);
    }

    @GetMapping()
    public ResponseEntity<Page<RelatorioDTO>> listaRelatorioPaginacao(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<RelatorioDTO> relatoriosPage = relatorioService.listaPaginacaoRelatorio(pageable)
                .map(RelatorioMapper::toDTO);
        return ResponseEntity.ok(relatoriosPage);
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

    @GetMapping("/nome-ordenado")
    public ResponseEntity<List<RelatorioDTO>> listaResponsavelPorNome() {
        RelatorioStrategy strategy = new NomeResponsavelStrategy();
        List<RelatorioDTO> listarNomes = relatorioService.listaOrganizadaRelatorio(strategy).stream().map(RelatorioMapper::toDTO).toList();
        return ResponseEntity.ok(listarNomes);
    }

    @GetMapping("/data-recente")
    public ResponseEntity<List<RelatorioDTO>> listarRelatoriosPorDataRecente() {
        RelatorioStrategy strategy = new DataRecenteStrategy();
        List<RelatorioDTO> listarData = relatorioService.listaOrganizadaRelatorio(strategy).stream().map(RelatorioMapper::toDTO).toList();
        return ResponseEntity.ok(listarData);
    }

    @GetMapping("/data-antiga")
    public ResponseEntity<List<RelatorioDTO>> listarRelatoriosPorDataAntiga() {
        RelatorioStrategy strategy = new DataAntigaStrategy();
        List<RelatorioDTO> listarData = relatorioService.listaOrganizadaRelatorio(strategy).stream().map(RelatorioMapper::toDTO).toList();
        return ResponseEntity.ok(listarData);
    }
}
