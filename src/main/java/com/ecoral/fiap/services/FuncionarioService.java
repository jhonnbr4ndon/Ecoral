package com.ecoral.fiap.services;

import com.ecoral.fiap.entities.Funcionario;
import com.ecoral.fiap.entities.dto.FuncionarioDTO;
import com.ecoral.fiap.repositories.FuncionarioRepository;
import com.ecoral.fiap.services.Exceptions.ResourceNotFoundException;
import com.ecoral.fiap.services.mapper.FuncionarioMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Transactional
    public FuncionarioDTO criarFuncionario(Funcionario funcionario) {
        funcionario = funcionarioRepository.save(funcionario);
        return FuncionarioMapper.toDTO(funcionario);
    }

    public List<FuncionarioDTO> listaFuncionario() {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        return funcionarios.stream()
                .map(FuncionarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    public FuncionarioDTO encontrarFuncionarioPorId(Long id) {
        Optional<Funcionario> optionalFuncionario = funcionarioRepository.findById(id);
        if (optionalFuncionario.isPresent()) {
            Funcionario funcionario = optionalFuncionario.get();
            return FuncionarioMapper.toDTO(funcionario);
        } else {
            throw new ResourceNotFoundException("Funcionario não encontrado com o ID: " + id);
        }
    }

    @Transactional
    public FuncionarioDTO atualizarFuncionario(Long id, FuncionarioDTO funcionarioDTO) {
        Optional<Funcionario> optionalFuncionario = funcionarioRepository.findById(id);
        if (optionalFuncionario.isPresent()) {
            Funcionario funcionario = optionalFuncionario.get();
            funcionario.setNome(funcionarioDTO.getNome());
            funcionario.setEmail(funcionarioDTO.getEmail());
            funcionario.setSenha(funcionarioDTO.getSenha());
            return FuncionarioMapper.toDTO(funcionario);
        } else {
            throw new ResourceNotFoundException("Funcionario não encontrado com o ID: " + id);
        }
    }

    @Transactional
    public void deletarFuncionario(Long id) {
        Optional<Funcionario> optionalFuncionario = funcionarioRepository.findById(id);
        if (optionalFuncionario.isPresent()) {
            funcionarioRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Funcionario não encontrado com o ID: " + id);
        }
    }
}
