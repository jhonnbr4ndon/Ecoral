package com.aquapure.fiap.entities.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class ParceirosDTO {

    @NotBlank(message = "Nome não pode estar em branco")
    private String nome;
    @NotBlank(message = "CNPJ não pode estar em branco")
    @Pattern(regexp = "\\d{14}", message = "CNPJ inválido. Deve conter 14 dígitos numéricos")
    private String cnpj;
    @NotBlank(message = "Endereço não pode estar em branco")
    private String endereco;
    @NotBlank(message = "Contato não pode estar em branco")
    private String contato;
    @NotBlank(message = "Tipo não pode estar em branco")
    private String tipo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
