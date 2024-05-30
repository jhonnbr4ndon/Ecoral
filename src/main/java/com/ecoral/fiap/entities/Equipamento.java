package com.ecoral.fiap.entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "equipamento")
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_equipamento")
    private Long id;
    @Column(name = "tipo_equipamento")
    private String tipo;
    @Column(name = "dt_instalacao")
    private Date data;
    @Column(name = "status_equipamento")
    private String status;

    @ManyToOne
    @JoinColumn(name = "id_parceiro")
    private Parceiro parceiro;

    @OneToMany(mappedBy = "equipamento")
    private List<Manutencao> manutencoes;

    @OneToMany(mappedBy = "equipamento")
    private List<Alerta> alertas;

    @OneToMany(mappedBy = "equipamento")
    private List<Dados> leituras;

    @OneToOne(mappedBy = "equipamento", cascade = CascadeType.ALL)
    private Local local;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
