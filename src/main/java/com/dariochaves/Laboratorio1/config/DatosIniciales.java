package com.dariochaves.Laboratorio1.config;

import com.dariochaves.Laboratorio1.repository.RoleRepository;
import com.dariochaves.Laboratorio1.repository.UsuarioRepository;
import com.dariochaves.Laboratorio1.entity.Usuario;
import com.dariochaves.Laboratorio1.entity.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatosIniciales {

    @Bean
    public CommandLineRunner cargarDatos(RoleRepository roleRepository, UsuarioRepository usuarioRepository) {
        return args -> {
            Role superAdminRole = roleRepository.findByNombre("SUPER-ADMIN-ROLE").orElseGet(() -> {
                Role role = new Role();
                role.setNombre("SUPER-ADMIN-ROLE");
                return roleRepository.save(role);
            });

            Role userRole = roleRepository.findByNombre("USER").orElseGet(() -> {
                Role role = new Role();
                role.setNombre("USER");
                return roleRepository.save(role);
            });

            if (usuarioRepository.findByUsername("admin").isEmpty()) {
                Usuario admin = new Usuario();
                admin.setUsername("admin");
                admin.setPassword(new BCryptPasswordEncoder().encode("admin7685"));
                admin.setRole(superAdminRole);
                usuarioRepository.save(admin);
            }

            if (usuarioRepository.findByUsername("user").isEmpty()) {
                Usuario user = new Usuario();
                user.setUsername("user");
                user.setPassword(new BCryptPasswordEncoder().encode("user7685"));
                user.setRole(userRole);
                usuarioRepository.save(user);
            }

        };
    }

}
