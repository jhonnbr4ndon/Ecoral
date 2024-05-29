package com.aquapure.fiap.services.mapper;

import com.aquapure.fiap.entities.Parceiro;
import com.aquapure.fiap.entities.dto.ParceirosDTO;

public class ParceiroMapper {

    public static Parceiro toEntity(ParceirosDTO parceirosDTO) {
        Parceiro parceiro = new Parceiro();
        parceiro.setNome(parceirosDTO.getNome());
        parceiro.setCnpj(parceirosDTO.getCnpj());
        parceiro.setEndereco(parceirosDTO.getEndereco());
        parceiro.setContato(parceirosDTO.getContato());
        parceiro.setTipo(parceirosDTO.getTipo());
        return parceiro;
    }

    public static ParceirosDTO toDTO(Parceiro parceiro) {
        ParceirosDTO parceirosDTO = new ParceirosDTO();
        parceirosDTO.setNome(parceiro.getNome());
        parceirosDTO.setCnpj(parceiro.getCnpj());
        parceirosDTO.setEndereco(parceiro.getEndereco());
        parceirosDTO.setContato(parceiro.getContato());
        parceirosDTO.setTipo(parceiro.getTipo());
        return parceirosDTO;
    }
}
