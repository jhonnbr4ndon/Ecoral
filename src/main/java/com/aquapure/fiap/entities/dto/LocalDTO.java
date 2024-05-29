package com.aquapure.fiap.entities.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LocalDTO {

    @NotBlank(message = "Nome não pode estar em branco")
    private String nome;

    @NotNull(message = "Latitude não pode ser nula")
    private Integer latitude;

    @NotNull(message = "Longitude não pode ser nula")
    private Integer longitude;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getLatitude() {
        return latitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }

    public Integer getLongitude() {
        return longitude;
    }

    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }
}
