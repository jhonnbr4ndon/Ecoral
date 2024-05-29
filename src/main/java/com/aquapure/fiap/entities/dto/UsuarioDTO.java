package com.aquapure.fiap.entities.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

@Validated
public class UsuarioDTO {

    @NotBlank(message = "Nome não pode estar em branco")
    private String nome;
    @NotBlank(message = "Email não pode estar em branco")
    @Email(message = "Email inválido")
    private String email;
    @NotBlank(message = "Senha não pode estar em branco")
    @Size(min = 8, max = 8, message = "A senha deve ter exatamente 8 caracteres")
    private String senha;
    @NotBlank(message = "Tipo não pode estar em branco")
    private String tipo;

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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
