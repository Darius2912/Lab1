package com.dariochaves.Laboratorio1.controller;


import com.dariochaves.Laboratorio1.dto.LoginRequest;
import com.dariochaves.Laboratorio1.dto.LoginResponse;
import com.dariochaves.Laboratorio1.dto.RegistroRequest;
import com.dariochaves.Laboratorio1.dto.UsuarioResponse;
import com.dariochaves.Laboratorio1.security.JwtService;
import com.dariochaves.Laboratorio1.security.UserDetailsServiceImpl;
import com.dariochaves.Laboratorio1.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtService jwtService;
    private final UsuarioService usuarioService;

    public AuthController(AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService, JwtService jwtService, UsuarioService usuarioService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtService.generarToken(userDetails);

        return new LoginResponse(token);
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioResponse> registrar (@Valid @RequestBody RegistroRequest request) {
        UsuarioResponse usuario = usuarioService.registrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }
}
