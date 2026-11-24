package com.clinica;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping({"/", "/login"})
    public String telaLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String entrar(@RequestParam String email, @RequestParam String senha, HttpSession session) {

        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario == null || !usuario.getSenha().equals(senha)) {
            return "redirect:/login?erro";
        }

        session.setAttribute("usuario", usuario.getNome());
        return "redirect:/agendamentos";
    }

    @GetMapping("/sair")
    public String sair(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}