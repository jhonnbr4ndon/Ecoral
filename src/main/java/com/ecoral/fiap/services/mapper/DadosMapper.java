package com.ecoral.fiap.services.mapper;

import com.ecoral.fiap.entities.Dados;
import com.ecoral.fiap.entities.dto.DadosDTO;

public class DadosMapper {

    public static Dados toEntity(DadosDTO dadosDTO) {
        Dados dados = new Dados();
        dados.setData(dadosDTO.getData());
        dados.setValor(dadosDTO.getValor());
        dados.setTipo(dadosDTO.getTipo());
        return dados;
    }

    public static DadosDTO toDTO(Dados dados) {
        DadosDTO dadosDTO = new DadosDTO();
        dadosDTO.setData(dados.getData());
        dadosDTO.setValor(dados.getValor());
        dadosDTO.setTipo(dados.getTipo());
        return dadosDTO;
    }
}
