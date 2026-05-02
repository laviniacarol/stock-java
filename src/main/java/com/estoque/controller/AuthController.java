package com.estoque.controller;

import com.estoque.dto.LoginRequest;
import com.estoque.dto.LoginResponse;
import com.estoque.model.Usuario;
import com.estoque.repository.UsuarioRepository;
import com.estoque.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Usuario usuario = usuarioRepository.findByUsername(request.getUsername())
                .orElse(null);

        if (usuario == null || !passwordEncoder.matches(request.getSenha(), usuario.getSenha())) {
            return ResponseEntity.status(401).body("Usuário ou senha inválidos");
        }

        String token = jwtUtil.generateToken(usuario.getUsername(), usuario.getPerfil());
        return ResponseEntity.ok(new LoginResponse(token, usuario.getUsername(), usuario.getPerfil()));
    }
}
