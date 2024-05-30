package com.ecoral.fiap.services.mapper;

import com.ecoral.fiap.entities.Manutencao;
import com.ecoral.fiap.entities.dto.ManutencaoDTO;

public class ManutencaoMapper {

    public static Manutencao toEntity(ManutencaoDTO manutencaoDTO) {
        Manutencao manutencao = new Manutencao();
        manutencao.setData(manutencaoDTO.getData());
        manutencao.setTipo(manutencaoDTO.getTipo());
        manutencao.setResponsavel(manutencaoDTO.getResponsavel());
        return  manutencao;
    }

    public static ManutencaoDTO toDTO(Manutencao manutencao) {
        ManutencaoDTO manutencaoDTO = new ManutencaoDTO();
        manutencaoDTO.setData(manutencao.getData());
        manutencaoDTO.setTipo(manutencao.getTipo());
        manutencaoDTO.setResponsavel(manutencao.getResponsavel());
        return manutencaoDTO;
    }
}
