package com.ecoral.fiap.services.mapper;

import com.ecoral.fiap.entities.Funcionario;
import com.ecoral.fiap.entities.dto.FuncionarioDTO;

public class FuncionarioMapper {

    public static Funcionario toEntity(FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(funcionarioDTO.getNome());
        funcionario.setEmail(funcionarioDTO.getEmail());
        funcionario.setSenha(funcionarioDTO.getSenha());
        return funcionario;
    }

    public static FuncionarioDTO toDTO(Funcionario funcionario) {
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
        funcionarioDTO.setNome(funcionario.getNome());
        funcionarioDTO.setEmail(funcionario.getEmail());
        funcionarioDTO.setSenha(funcionario.getSenha());
        return funcionarioDTO;
    }
}
