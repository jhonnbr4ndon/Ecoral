package com.ecoral.fiap.entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "relatorio")
public class Relatorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_relatorio")
    private Long id;
    @Column(name = "desc_relatorio")
    private String descricao;
    @Column(name = "dt_relatorio")
    private Date data;
    @Column(name = "respon_relatorio")
    private String responsavel;
    @Column(name = "img_relatorio")
    private String imagem;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToMany
    @JoinTable(name = "relatorio_parceiro",
            joinColumns = @JoinColumn(name = "id_relatorio"),
            inverseJoinColumns = @JoinColumn(name = "id_parceiro"))
    private List<Parceiro> parceiros;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public void setReponsavel(String reponsavel) {
        this.responsavel = reponsavel;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
