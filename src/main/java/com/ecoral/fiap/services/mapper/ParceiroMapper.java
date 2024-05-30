package com.ecoral.fiap.services.mapper;

import com.ecoral.fiap.entities.Parceiro;
import com.ecoral.fiap.entities.dto.ParceiroDTO;

public class ParceiroMapper {

    public static Parceiro toEntity(ParceiroDTO parceiroDTO) {
        Parceiro parceiro = new Parceiro();
        parceiro.setNome(parceiroDTO.getNome());
        parceiro.setCnpj(parceiroDTO.getCnpj());
        parceiro.setEndereco(parceiroDTO.getEndereco());
        parceiro.setContato(parceiroDTO.getContato());
        parceiro.setTipo(parceiroDTO.getTipo());
        return parceiro;
    }

    public static ParceiroDTO toDTO(Parceiro parceiro) {
        ParceiroDTO parceiroDTO = new ParceiroDTO();
        parceiroDTO.setNome(parceiro.getNome());
        parceiroDTO.setCnpj(parceiro.getCnpj());
        parceiroDTO.setEndereco(parceiro.getEndereco());
        parceiroDTO.setContato(parceiro.getContato());
        parceiroDTO.setTipo(parceiro.getTipo());
        return parceiroDTO;
    }
}
