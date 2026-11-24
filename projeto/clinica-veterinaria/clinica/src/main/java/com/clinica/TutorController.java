package com.clinica;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TutorController {

    @Autowired
    private TutorRepository tutorRepository;

    @GetMapping("/tutores")
    public String listar(@RequestParam(defaultValue = "") String termo, Model model, HttpSession session) {

        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }

        model.addAttribute("tutores", tutorRepository.findByNomeContainingIgnoreCase(termo));
        model.addAttribute("termo", termo);
        return "tutores";
    }

    @PostMapping("/tutores")
    public String salvar(Tutor tutor, HttpSession session) {

        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }

        tutorRepository.save(tutor);
        return "redirect:/tutores";
    }
}