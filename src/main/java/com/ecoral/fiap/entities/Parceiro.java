package com.ecoral.fiap.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "parceiro")
public class Parceiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_parceiro")
    private Long id;
    @Column(name = "nome_parceiro")
    private String nome;
    @Column(name = "cnpj_parceiro")
    private String cnpj;
    @Column(name = "endereco_parceiro")
    private String endereco;
    @Column(name = "contato_parceiro")
    private String contato;
    @Column(name = "tipo_parceiro")
    private String tipo;

//    @ManyToMany(mappedBy = "parceiros")
//    private List<Relatorio> relatorios;
//
//    @OneToMany(mappedBy = "parceiro")
//    private List<Equipamento> equipamentos;

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
