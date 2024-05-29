package com.aquapure.fiap.services;

import com.aquapure.fiap.entities.Usuario;
import com.aquapure.fiap.entities.dto.UsuarioDTO;
import com.aquapure.fiap.repositories.UsuarioRepository;
import com.aquapure.fiap.services.Exceptions.ResourceNotFoundException;
import com.aquapure.fiap.services.mapper.UsuarioMapper;
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
