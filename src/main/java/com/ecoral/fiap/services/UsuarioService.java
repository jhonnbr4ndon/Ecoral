package com.ecoral.fiap.services;

import com.ecoral.fiap.entities.Usuario;
import com.ecoral.fiap.entities.dto.UsuarioDTO;
import com.ecoral.fiap.repositories.UsuarioRepository;
import com.ecoral.fiap.services.Exceptions.ResourceNotFoundException;
import com.ecoral.fiap.services.mapper.UsuarioMapper;
import com.ecoral.fiap.strategies.usuario.UsuarioStrategy;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioStrategy usuarioStrategy;

    @Autowired
    public void setUsuarioStrategy(UsuarioStrategy usuarioStrategy) {
        this.usuarioStrategy = usuarioStrategy;
    }

    public List<Usuario> listaOrganizadaUsuario(UsuarioStrategy strategy) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return strategy.organizar(usuarios);
    }

    @Transactional
    public UsuarioDTO criarUsuario(Usuario usuario) {
        usuario = usuarioRepository.save(usuario);
        return UsuarioMapper.toDTO(usuario);
    }

    public List<UsuarioDTO> listaUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(UsuarioMapper::toDTO)
                .collect(Collectors.toList());
    }


    public UsuarioDTO encontrarUsuarioPorId(Long id) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            return UsuarioMapper.toDTO(usuario);
        } else {
            throw new ResourceNotFoundException("Usuário não encontrado com o ID: " + id);
        }
    }


    @Transactional
    public UsuarioDTO atualizarUsuario(Long id, UsuarioDTO usuarioDTO) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            usuario.setNome(usuarioDTO.getNome());
            usuario.setEmail(usuarioDTO.getEmail());
            usuario.setSenha(usuarioDTO.getSenha());
            usuario.setTipo(usuarioDTO.getTipo());
            usuario = usuarioRepository.save(usuario);
            return UsuarioMapper.toDTO(usuario);
        } else {
            throw new ResourceNotFoundException("Usuário não encontrado com o ID: " + id);
        }
    }

    @Transactional
    public void deletarUsuario(Long id) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isPresent()) {
            usuarioRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Usuário não encontrado com o ID: " + id);
        }
    }
}
