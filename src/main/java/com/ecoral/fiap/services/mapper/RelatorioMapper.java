package com.ecoral.fiap.services.mapper;

import com.ecoral.fiap.entities.Relatorio;
import com.ecoral.fiap.entities.dto.RelatorioDTO;

public class RelatorioMapper {

    public static Relatorio toEntity(RelatorioDTO relatorioDTO) {
        Relatorio relatorio = new Relatorio();
        relatorio.setDescricao(relatorioDTO.getDescricao());
        relatorio.setData(relatorioDTO.getData());
        relatorio.setReponsavel(relatorioDTO.getReponsavel());
        relatorio.setImagem(relatorioDTO.getImagem());
        return relatorio;
    }

    public static RelatorioDTO toDTO(Relatorio relatorio) {
        RelatorioDTO relatorioDTO = new RelatorioDTO();
        relatorioDTO.setDescricao(relatorio.getDescricao());
        relatorioDTO.setData(relatorio.getData());
        relatorioDTO.setReponsavel(relatorio.getReponsavel());
        relatorioDTO.setImagem(relatorio.getImagem());
        return relatorioDTO;
    }
}
