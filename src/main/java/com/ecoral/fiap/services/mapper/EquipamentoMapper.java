package com.ecoral.fiap.services.mapper;

import com.ecoral.fiap.entities.Equipamento;
import com.ecoral.fiap.entities.dto.EquipamentoDTO;

public class EquipamentoMapper {

    public static Equipamento toEntity(EquipamentoDTO equipamentoDTO) {
        Equipamento equipamento = new Equipamento();
        equipamento.setTipo(equipamentoDTO.getTipo());
        equipamento.setData(equipamentoDTO.getData());
        equipamento.setStatus(equipamentoDTO.getStatus());
        return equipamento;
    }

    public static EquipamentoDTO toDTO(Equipamento equipamento) {
        EquipamentoDTO equipamentoDTO = new EquipamentoDTO();
        equipamentoDTO.setTipo(equipamento.getTipo());
        equipamentoDTO.setData(equipamento.getData());
        equipamentoDTO.setStatus(equipamento.getStatus());
        return equipamentoDTO;
    }
}
