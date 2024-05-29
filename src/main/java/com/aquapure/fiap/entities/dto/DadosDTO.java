package com.aquapure.fiap.entities.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;

public class DadosDTO {

    @NotNull(message = "Data não pode ser nula")
    private Date data;

    @NotBlank(message = "Valor não pode estar em branco")
    private String valor;

    @NotBlank(message = "Tipo não pode estar em branco")
    private String tipo;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
