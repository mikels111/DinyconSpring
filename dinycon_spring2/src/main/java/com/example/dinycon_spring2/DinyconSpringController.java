package com.example.dinycon_spring2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class DinyconSpringController {
    @GetMapping("/")
    public String index(Model model) {
		model.addAttribute("nombre","Inicio");
		model.addAttribute("content","");
        return "index";
    }

    @GetMapping("/greetings")
    public String greetings(Model model) {
        model.addAttribute("nombre","Greetings");
        model.addAttribute("content","");
        return "index";
    }

    @GetMapping("/home")
    public String iniciarSesion(Model model) {
        model.addAttribute("nombre","IniciarSesion");
        model.addAttribute("content","../fragments/greetingsContent");
        return "index";
    }
}
