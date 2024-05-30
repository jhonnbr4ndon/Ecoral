package com.ecoral.fiap.strategies.usuario;

import com.ecoral.fiap.entities.Usuario;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Primary
@Component
public class NomeUsuarioStrategy implements UsuarioStrategy {

    @Override
    public List<Usuario> organizar(List<Usuario> usuarios) {
        return usuarios.stream()
                .sorted(Comparator.comparing(Usuario::getNome))
                .collect(Collectors.toList());
    }
}
