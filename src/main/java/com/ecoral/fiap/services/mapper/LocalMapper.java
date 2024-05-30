package com.ecoral.fiap.services.mapper;

import com.ecoral.fiap.entities.Local;
import com.ecoral.fiap.entities.dto.LocalDTO;

public class LocalMapper {

    public static Local toEntity(LocalDTO localDTO) {
        Local local = new Local();
        local.setNome(localDTO.getNome());
        local.setLatitude(localDTO.getLatitude());
        local.setLongitude(localDTO.getLongitude());
        return local;
    }

    public static LocalDTO toDTO(Local local) {
        LocalDTO localDTO = new LocalDTO();
        localDTO.setNome(local.getNome());
        localDTO.setLatitude(local.getLatitude());
        localDTO.setLongitude(local.getLongitude());
        return localDTO;
    }
}
