package com.ecoral.fiap.services;

import com.ecoral.fiap.entities.Parceiro;
import com.ecoral.fiap.entities.dto.ParceiroDTO;
import com.ecoral.fiap.repositories.ParceirosRepository;
import com.ecoral.fiap.services.Exceptions.ResourceNotFoundException;
import com.ecoral.fiap.services.mapper.ParceiroMapper;
import com.ecoral.fiap.strategies.Parceiro.ParceiroStrategy;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParceiroService {

    @Autowired
    private ParceirosRepository parceirosRepository;

    @Autowired
    private ParceiroStrategy parceiroStrategy;

    @Autowired
    private void setParceiroStrategy(ParceiroStrategy parceiroStrategy){
        this.parceiroStrategy = parceiroStrategy;
    }

    public List<Parceiro> listaOrganizadaParceiro(ParceiroStrategy parceiroStrategy) {
        List<Parceiro> parceiros = parceirosRepository.findAll();
        return parceiroStrategy.organizar(parceiros);
    }

    @Transactional
    public ParceiroDTO criarParceiro(Parceiro parceiro) {
        parceiro = parceirosRepository.save(parceiro);
        return ParceiroMapper.toDTO(parceiro);
    }

    public List<ParceiroDTO> listaParceiro() {
        List<Parceiro> parceiros = parceirosRepository.findAll();
        return parceiros.stream()
                .map(ParceiroMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ParceiroDTO encontrarParceiroPorId(Long id) {
        Optional<Parceiro> optionalParceiro = parceirosRepository.findById(id);
        if (optionalParceiro.isPresent()) {
            Parceiro parceiro = optionalParceiro.get();
            return ParceiroMapper.toDTO(parceiro);
        } else {
            throw new ResourceNotFoundException("Parceiro não encontrado com o ID: " + id);
        }
    }

    @Transactional
    public ParceiroDTO atualizarParceiro(Long id, ParceiroDTO parceiroDTO) {
        Optional<Parceiro> optionalParceiro  = parceirosRepository.findById(id);
        if (optionalParceiro.isPresent()) {
            Parceiro parceiro = optionalParceiro.get();
            parceiro.setNome(parceiroDTO.getNome());
            parceiro.setCnpj(parceiroDTO.getCnpj());
            parceiro.setEndereco(parceiroDTO.getEndereco());
            parceiro.setContato(parceiroDTO.getContato());
            parceiro.setTipo(parceiroDTO.getTipo());
            return ParceiroMapper.toDTO(parceiro);
        } else {
            throw new ResourceNotFoundException("Parceiro não encontrado com o ID: " + id);
        }
    }

    @Transactional
    public void deletarParceiro(Long id) {
        Optional<Parceiro> optionalParceiro = parceirosRepository.findById(id);
        if (optionalParceiro.isPresent()) {
            parceirosRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Parceiro não encontrado com o ID: " + id);
        }
    }
}
