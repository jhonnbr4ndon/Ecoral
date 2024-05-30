package com.ecoral.fiap.services;

import com.ecoral.fiap.entities.Local;
import com.ecoral.fiap.entities.dto.LocalDTO;
import com.ecoral.fiap.repositories.LocalRepository;
import com.ecoral.fiap.services.Exceptions.ResourceNotFoundException;
import com.ecoral.fiap.services.mapper.LocalMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LocalService {

    @Autowired
    private LocalRepository localRepository;

    @Transactional
    public LocalDTO criarLocal(Local local) {
        local = localRepository.save(local);
        return LocalMapper.toDTO(local);
    }

    public List<LocalDTO> listaLocal() {
        List<Local> locals = localRepository.findAll();
        return locals.stream()
                .map(LocalMapper::toDTO)
                .collect(Collectors.toList());
    }

    public LocalDTO encontrarLocalPorId(Long id) {
        Optional<Local> optionalLocal = localRepository.findById(id);
        if (optionalLocal.isPresent()) {
            Local local = optionalLocal.get();
            return LocalMapper.toDTO(local);
        } else {
            throw new ResourceNotFoundException("Local não encontrado com o ID: " + id);
        }
    }

    @Transactional
    public LocalDTO atualizarLocal(Long id, LocalDTO localDTO) {
        Optional<Local> optionalLocal = localRepository.findById(id);
        if (optionalLocal.isPresent()) {
            Local local = optionalLocal.get();
            local.setNome(localDTO.getNome());
            local.setLatitude(localDTO.getLatitude());
            local.setLongitude(localDTO.getLongitude());
            return LocalMapper.toDTO(local);
        } else {
            throw new ResourceNotFoundException("Local não encontrado com o ID: " + id);
        }
    }

    @Transactional
    public void deletarLocal(Long id) {
        Optional<Local> optionalLocal = localRepository.findById(id);
        if (optionalLocal.isPresent()) {
            localRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Local não encontrado com o ID: " + id);
        }
    }
}
