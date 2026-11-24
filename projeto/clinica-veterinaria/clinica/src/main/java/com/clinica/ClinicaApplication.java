package com.clinica;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClinicaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClinicaApplication.class, args);
    }


    @Bean
    CommandLineRunner criarUsuario(UsuarioRepository usuarioRepository) {
        return args -> {
            if (usuarioRepository.findByEmail("admin@clinica.com") == null) {
                Usuario usuario = new Usuario();
                usuario.setNome("Administrador");
                usuario.setEmail("admin@clinica.com");
                usuario.setSenha("123456");
                usuarioRepository.save(usuario);
            }
        };
    }
}
