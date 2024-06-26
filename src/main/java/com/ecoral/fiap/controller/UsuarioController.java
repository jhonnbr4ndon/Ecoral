package com.ecoral.fiap.controller;

import com.ecoral.fiap.entities.Usuario;
import com.ecoral.fiap.entities.dto.UsuarioDTO;
import com.ecoral.fiap.services.Exceptions.ResourceNotFoundException;
import com.ecoral.fiap.services.UsuarioService;
import com.ecoral.fiap.services.mapper.UsuarioMapper;
import com.ecoral.fiap.strategies.usuario.NomeUsuarioStrategy;
import com.ecoral.fiap.strategies.usuario.UsuarioStrategy;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping()
    public ResponseEntity<List<UsuarioDTO>> listaUsuarios() {
        List<UsuarioDTO> usuarioDTOS = usuarioService.listaUsuarios();
        return ResponseEntity.ok(usuarioDTOS);
    }

    @PostMapping()
    public ResponseEntity<UsuarioDTO> criarUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = UsuarioMapper.toEntity(usuarioDTO);
        UsuarioDTO novoUsuario = usuarioService.criarUsuario(usuario);
        return ResponseEntity.ok(novoUsuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> encontrarUsuarioPorID(@PathVariable Long id) {
        try {
            UsuarioDTO usuarioDTO = usuarioService.encontrarUsuarioPorId(id);
            return ResponseEntity.ok(usuarioDTO);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Usuário não encontrado com o ID: " + id);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizaUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioDTO usuarioDTO) {
        try {
            UsuarioDTO usuarioAtualizado = usuarioService.atualizarUsuario(id, usuarioDTO);
            return ResponseEntity.ok(usuarioAtualizado);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Usuário não encontrado com o ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarUsuario(@PathVariable Long id) {
        try {
            usuarioService.deletarUsuario(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Usuário não encontrado com o ID: " + id);
        }
    }

    @GetMapping("/nome-ordenado")
    public ResponseEntity<List<UsuarioDTO>> listaUsuarioPorNome() {
        UsuarioStrategy strategy = new NomeUsuarioStrategy();
        List<UsuarioDTO> listarNomes = usuarioService.listaOrganizadaUsuario(strategy).stream().map(UsuarioMapper::toDTO).toList();
        return ResponseEntity.ok(listarNomes);
    }

    //Endpoint para fazer Authenticação na parte do login do front-end.
    @PostMapping("/login")
    public ResponseEntity<?> loginUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        try {
            UsuarioDTO usuarioAutenticado = usuarioService.autenticarUsuario(usuarioDTO.getEmail(), usuarioDTO.getSenha());
            if (usuarioAutenticado != null) {
                return ResponseEntity.ok(usuarioAutenticado);
            } else {
                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body("Usuário ou senha incorretos");
            }
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro durante a autenticação do usuário: " + ex.getMessage());
        }
    }
}
