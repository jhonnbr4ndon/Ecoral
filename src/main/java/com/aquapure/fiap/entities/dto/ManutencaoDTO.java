package com.aquapure.fiap.entities.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;

public class ManutencaoDTO {

    @NotNull(message = "Data não pode estar em branco")
    private Date data;

    @NotBlank(message = "Tipo não pode estar em branco")
    private String tipo;

    @NotBlank(message = "Responsável não pode estar em branco")
    private String responsavel;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }
}
