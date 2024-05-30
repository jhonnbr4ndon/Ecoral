package com.ecoral.fiap.services;

import com.ecoral.fiap.entities.Manutencao;
import com.ecoral.fiap.entities.dto.ManutencaoDTO;
import com.ecoral.fiap.repositories.ManutencaoRepository;
import com.ecoral.fiap.services.Exceptions.ResourceNotFoundException;
import com.ecoral.fiap.services.mapper.ManutencaoMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ManutencaoService {

    @Autowired
    private ManutencaoRepository manutencaoRepository;

    @Transactional
    public ManutencaoDTO criarManutencao(Manutencao manutencao) {
        manutencao = manutencaoRepository.save(manutencao);
        return ManutencaoMapper.toDTO(manutencao);
    }

    public List<ManutencaoDTO> listaManutencao() {
        List<Manutencao> manutencaos = manutencaoRepository.findAll();
        return manutencaos.stream()
                .map(ManutencaoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ManutencaoDTO encontrarManutencaoPorId(Long id) {
        Optional<Manutencao> optionalManutencao = manutencaoRepository.findById(id);
        if (optionalManutencao.isPresent()) {
            Manutencao manutencao = optionalManutencao.get();
            return ManutencaoMapper.toDTO(manutencao);
        } else {
            throw new ResourceNotFoundException("Manutenção não encontrado com o ID: " + id);
        }
    }

    @Transactional
    public ManutencaoDTO atualizarManutencao(Long id, ManutencaoDTO manutencaoDTO) {
        Optional<Manutencao> optionalManutencao = manutencaoRepository.findById(id);
        if (optionalManutencao.isPresent()) {
            Manutencao manutencao = optionalManutencao.get();
            manutencao.setData(manutencaoDTO.getData());
            manutencao.setTipo(manutencaoDTO.getTipo());
            manutencao.setResponsavel(manutencaoDTO.getResponsavel());
            return ManutencaoMapper.toDTO(manutencao);
        } else {
            throw new ResourceNotFoundException("Manutenção não encontrado com o ID: " + id);
        }
    }

    @Transactional
    public void deletarManutencao(Long id) {
        Optional<Manutencao> optionalManutencao = manutencaoRepository.findById(id);
        if (optionalManutencao.isPresent()) {
            manutencaoRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Manutenção não encontrado com o ID: " + id);
        }
    }
}
