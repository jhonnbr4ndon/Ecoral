package com.ecoral.fiap.entities.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;

public class AlertaDTO {

    @NotNull(message = "Data não pode ser nula")
    private Date data;

    @NotBlank(message = "Nível não pode estar em branco")
    private String nivel;

    @NotBlank(message = "Descrição não pode estar em branco")
    private String descricao;

    @NotBlank(message = "Status não pode estar em branco")
//  @Pattern(regexp = "ATIVO|INATIVO", message = "Status inválido. Deve ser 'ATIVO' ou 'INATIVO'")
    private String status;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
