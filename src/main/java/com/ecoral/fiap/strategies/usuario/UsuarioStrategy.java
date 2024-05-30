package com.ecoral.fiap.strategies.usuario;

import com.ecoral.fiap.entities.Usuario;

import java.util.List;

public interface UsuarioStrategy {

    List<Usuario> organizar(List<Usuario> usuarios);
}
