package com.ecoral.fiap.entities.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;

public class EquipamentoDTO {

    @NotBlank(message = "Tipo não pode estar em branco")
    private String tipo;

    @NotNull(message = "Data não pode ser nula")
    private Date data;

    @NotBlank(message = "Status não pode estar em branco")
    private String status;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
