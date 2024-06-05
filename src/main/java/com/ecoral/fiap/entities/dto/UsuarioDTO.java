package com.ecoral.fiap.entities.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioDTO {

    private Long id;
    @NotBlank(message = "Nome não pode estar em branco")
    private String nome;
    @NotBlank(message = "Email não pode estar em branco")
    @Email(message = "Email inválido")
    private String email;
    @NotBlank(message = "Senha não pode estar em branco")
    @Size(min = 8, max = 8, message = "A senha deve ter exatamente 8 caracteres")
    private String senha;

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
