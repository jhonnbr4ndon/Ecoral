package com.aquapure.fiap.entities;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "alerta_equipamento")
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alerta")
    private Long id;
    @Column(name = "dt_alerta")
    private Date data;
    @Column(name = "nivel_alerta")
    private String nivel;
    @Column(name = "descricao_alerta")
    private String descricao;
    @Column(name = "status_alerta")
    private String status;

    @ManyToOne
    @JoinColumn(name = "id_equipamento")
    private Equipamento equipamento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
