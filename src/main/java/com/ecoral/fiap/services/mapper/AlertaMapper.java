package com.ecoral.fiap.services.mapper;

import com.ecoral.fiap.entities.Alerta;
import com.ecoral.fiap.entities.dto.AlertaDTO;

public class AlertaMapper {

    public static Alerta toEntity(AlertaDTO alertaDTO) {
        Alerta alerta = new Alerta();
        alerta.setData(alertaDTO.getData());
        alerta.setNivel(alertaDTO.getNivel());
        alerta.setDescricao(alertaDTO.getDescricao());
        alerta.setStatus(alertaDTO.getStatus());
        return alerta;
    }

    public static AlertaDTO toDTO(Alerta alerta) {
        AlertaDTO alertaDTO = new AlertaDTO();
        alertaDTO.setData(alerta.getData());
        alertaDTO.setNivel(alerta.getNivel());
        alertaDTO.setDescricao(alerta.getDescricao());
        alertaDTO.setStatus(alerta.getStatus());
        return alertaDTO;
    }
}
