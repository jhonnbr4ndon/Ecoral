package com.aquapure.fiap.services;

import com.aquapure.fiap.entities.Relatorio;
import com.aquapure.fiap.entities.dto.RelatorioDTO;
import com.aquapure.fiap.repositories.RelatorioRepository;
import com.aquapure.fiap.services.Exceptions.ResourceNotFoundException;
import com.aquapure.fiap.services.mapper.RelatorioMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RelatorioService {

    @Autowired
    private RelatorioRepository relatorioRepository;

    @Transactional
    public RelatorioDTO criarRelatorio(Relatorio relatorio) {
        relatorio = relatorioRepository.save(relatorio);
        return RelatorioMapper.toDTO(relatorio);
    }

    public List<RelatorioDTO> listaRelatorio() {
        List<Relatorio> relatorios = relatorioRepository.findAll();
        return  relatorios.stream()
                .map(RelatorioMapper::toDTO)
                .collect(Collectors.toList());
    }

    public RelatorioDTO encontrarRelatorioPorId(Long id) {
        Optional<Relatorio> optionalRelatorio = relatorioRepository.findById(id);
        if (optionalRelatorio.isPresent()) {
            Relatorio relatorio = optionalRelatorio.get();
            return RelatorioMapper.toDTO(relatorio);
        }
        else {
            throw new ResourceNotFoundException("Relátorio não encontrado com o ID: " + id);
        }
    }

    @Transactional
    public RelatorioDTO atualizarRelatorio(Long id, RelatorioDTO relatorioDTO) {
        Optional<Relatorio> optionalRelatorio = relatorioRepository.findById(id);
        if (optionalRelatorio.isPresent()) {
            Relatorio relatorio = optionalRelatorio.get();
            relatorio.setDescricao(relatorioDTO.getDescricao());
            relatorio.setData(relatorioDTO.getData());
            relatorio.setReponsavel(relatorioDTO.getReponsavel());
            relatorio.setImagem(relatorioDTO.getImagem());
            return RelatorioMapper.toDTO(relatorio);
        } else {
            throw new ResourceNotFoundException("Relátorio não encontrado com o ID: " + id);
        }
    }

    @Transactional
    public void deletarRelatorio(Long id) {
        Optional<Relatorio> optionalRelatorio = relatorioRepository.findById(id);
        if (optionalRelatorio.isPresent()) {
            relatorioRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Relátorio não encontrado com o ID: " + id);
        }
    }
}
