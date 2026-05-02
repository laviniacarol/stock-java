package com.estoque;

import com.estoque.model.Usuario;
import com.estoque.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class EstoqueApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(EstoqueApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (usuarioRepository.count() == 0) {
                usuarioRepository.save(new Usuario(null, "admin", passwordEncoder.encode("admin123"), "ADMINISTRADOR"));
                usuarioRepository.save(new Usuario(null, "gerente", passwordEncoder.encode("gerente123"), "GERENTE"));
            }
        };
    }
}
