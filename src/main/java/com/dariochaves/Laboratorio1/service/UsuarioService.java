package com.dariochaves.Laboratorio1.service;

import com.dariochaves.Laboratorio1.dto.RegistroRequest;
import com.dariochaves.Laboratorio1.dto.UsuarioResponse;
import com.dariochaves.Laboratorio1.model.Role;
import com.dariochaves.Laboratorio1.model.Usuario;
import com.dariochaves.Laboratorio1.exception.ConflictoException;
import com.dariochaves.Laboratorio1.exception.RecursoNoEncontado;
import com.dariochaves.Laboratorio1.repository.RoleRepository;
import com.dariochaves.Laboratorio1.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioResponse registrar(RegistroRequest request) {
        if
        (usuarioRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new ConflictoException("El username ya esta registrado" + request.getUsername());
        }
        Role rolUser = roleRepository.findByNombre("USER").orElseThrow(() -> new RecursoNoEncontado("Rol USER no encontrado"));
        Usuario usuario = new Usuario();
        usuario.setUsername(request.getUsername());

        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setRole(rolUser);

        Usuario guardado = usuarioRepository.save(usuario);
        return convertir(guardado);
    }

    public List<UsuarioResponse> listar() {
        return usuarioRepository.findAll().stream().map(this::convertir).toList();

        }

        private UsuarioResponse convertir(Usuario usuario) {
        return new UsuarioResponse(usuario.getId(), usuario.getUsername(), usuario.getRole().getNombre());
    }
}

