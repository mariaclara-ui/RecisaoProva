package com.clinica;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AgendamentoController {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private PetRepository petRepository;

    @GetMapping("/agendamentos")
    public String listar(@RequestParam(defaultValue = "") String termo, Model model, HttpSession session) {

        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }

        model.addAttribute("agendamentos", agendamentoRepository.findByPetNomeContainingIgnoreCaseOrderByDataHoraAsc(termo));
        model.addAttribute("pets", petRepository.findAll());
        model.addAttribute("termo", termo);
        return "agendamentos";
    }

    @PostMapping("/agendamentos")
    public String salvar(Agendamento agendamento, HttpSession session) {

        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }

        agendamentoRepository.save(agendamento);
        return "redirect:/agendamentos";
    }
}