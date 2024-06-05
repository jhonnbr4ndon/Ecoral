package com.ecoral.fiap.entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "leitura_equipamento")
public class Dados {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_leitura")
    private Long id;
    @Column(name = "dt_leitura")
    private Date data;
    @Column(name = "valor_leitura")//Valor da Leitura = ph, turbidez, temperatura, etc...
    private String valor;
    @Column(name = "tipo_leitura") //Tipo Leitura = ph, turbidez, temperatura, etc...
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_equipamento")
    private Equipamento equipamento;

    @ManyToOne
    @JoinColumn(name = "id_parceiro")
    private Parceiro parceiro;

    @ManyToOne
    @JoinColumn(name = "id_local")
    private Local local;

    @OneToMany(mappedBy = "dados", cascade = CascadeType.ALL)
    private List<Alerta> alertas;

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

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
