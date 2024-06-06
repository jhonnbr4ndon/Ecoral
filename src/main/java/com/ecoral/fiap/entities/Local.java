package com.ecoral.fiap.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "local_equipamento")
public class Local {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_local")
    private Long id;
    @Column(name = "nome_local")
    private String nome;
    @Column(name = "latitude_local")
    private Integer latitude;
    @Column(name = "longitude_local")
    private Integer longitude;

    @OneToMany(mappedBy = "local", cascade = CascadeType.ALL)
    private List<Dados> dados;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
