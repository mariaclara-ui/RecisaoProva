package com.clinica;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PetController {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private TutorRepository tutorRepository;

    @GetMapping("/pets")
    public String listar(Model model, HttpSession session) {

        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }

        model.addAttribute("pets", petRepository.findAll());
        model.addAttribute("tutores", tutorRepository.findAll());
        return "pets";
    }

    @PostMapping("/pets")
    public String salvar(Pet pet, HttpSession session) {

        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }

        petRepository.save(pet);
        return "redirect:/pets";
    }
}