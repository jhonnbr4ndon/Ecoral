package com.ecoral.fiap.entities.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.sql.Date;

public class RelatorioDTO {

    @NotBlank(message = "Descrição não pode estar em branco")
    @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
    private String descricao;
    @NotNull(message = "Data não pode estar em branco")
    private Date data;
    @NotBlank(message = "Responsável não pode estar em branco")
    private String responsavel;
    private String imagem;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String reponsavel) {
        this.responsavel = reponsavel;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
