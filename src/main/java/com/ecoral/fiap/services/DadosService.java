package com.ecoral.fiap.services;

import com.ecoral.fiap.entities.Dados;
import com.ecoral.fiap.entities.dto.DadosDTO;
import com.ecoral.fiap.repositories.DadosRepository;
import com.ecoral.fiap.services.Exceptions.ResourceNotFoundException;
import com.ecoral.fiap.services.mapper.DadosMapper;
import com.ecoral.fiap.strategies.dados.DadosStrategy;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DadosService {

    @Autowired
    private DadosRepository dadosRepository;

    @Autowired
    private DadosStrategy dadosStrategy;

    @Autowired
    private void setDadosStrategy(DadosStrategy dadosStrategy) {
        this.dadosStrategy = dadosStrategy;
    }

    public List<Dados> listaOrganizadaDados(DadosStrategy dadosStrategy) {
        List<Dados> dados =dadosRepository.findAll();
        return dadosStrategy.organizar(dados);
    }

    @Transactional
    public DadosDTO criarDados(Dados dados) {
        dados = dadosRepository.save(dados);
        return DadosMapper.toDTO(dados);
    }

    public List<DadosDTO> listaDados() {
        List<Dados> dados = dadosRepository.findAll();
        return dados.stream()
                .map(DadosMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Page<DadosDTO> listaPaginacaoDados(Pageable pageable) {
        return dadosRepository.findAll(pageable).map(DadosMapper::toDTO);
    }

    public DadosDTO encontrarDadosPorId(Long id) {
        Optional<Dados> optionalDados = dadosRepository.findById(id);
        if (optionalDados.isPresent()) {
            Dados dados = optionalDados.get();
            return DadosMapper.toDTO(dados);
        } else {
            throw new ResourceNotFoundException("Dados não encontrado com o ID: " + id);
        }
    }

    @Transactional
    public DadosDTO atualizarDado(Long id, DadosDTO dadosDTO) {
        Optional<Dados> optionalDados = dadosRepository.findById(id);
        if (optionalDados.isPresent()) {
            Dados dados = optionalDados.get();
            dados.setData(dadosDTO.getData());
            dados.setValor(dadosDTO.getValor());
            dados.setTipo(dadosDTO.getTipo());
            return DadosMapper.toDTO(dados);
        } else {
            throw new ResourceNotFoundException("Dados não encontrado com o ID: " + id);
        }
    }

    @Transactional
    public void deletarDado(Long id) {
        Optional<Dados> optionalDados = dadosRepository.findById(id);
        if (optionalDados.isPresent()) {
            dadosRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Dados não encontrado com o ID: " + id);
        }
    }
}
