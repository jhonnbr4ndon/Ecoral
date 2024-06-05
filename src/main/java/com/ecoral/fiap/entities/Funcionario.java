package com.ecoral.fiap.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "funcionario")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_funcionario")
    private Long id;
    @Column(name = "nome_funcionario")
    private String nome;
    @Column(name = "email_funcionario")
    private String email;
    @Column(name = "senha_funcionario")
    private String senha;

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL)
    private List<Relatorio> relatorios;

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL)
    private List<Manutencao> manutencoes;

    @ManyToOne
    @JoinColumn(name = "id_parceiro")
    private Parceiro parceiro;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
