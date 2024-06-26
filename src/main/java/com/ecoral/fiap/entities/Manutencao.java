package com.ecoral.fiap.entities;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "manutencao_equipamento")
public class Manutencao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_manutencao")
    private Long id;
    @Column(name = "dt_manutencao")
    private Date data;
    @Column(name = "tipo_manutencao")
    private String tipo;
    @Column(name = "responsavel_manutencao")
    private String responsavel;

    @ManyToOne
    @JoinColumn(name = "id_equipamento")
    private Equipamento equipamento;

    @OneToOne(mappedBy = "manutencao")
    private Relatorio relatorio;

    @ManyToOne
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "id_parceiro")
    private Parceiro parceiro;

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
