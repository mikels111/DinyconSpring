package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class IndexController {
	@GetMapping("/")
    public String index(Model model) {
		model.addAttribute("nombre","Inicio");
		model.addAttribute("content","");
        return "index";
    }

    @GetMapping("/greetings")
    public String greetings(Model model) {
        model.addAttribute("nombre","Greetings");
        model.addAttribute("content","../fragments/greetingsContent");
        return "index";
    }

    @GetMapping("/login")
    public String iniciarSesion(Model model) {
        model.addAttribute("nombre","IniciarSesion");
        model.addAttribute("content","../fragments/greetingsContent");
        return "index";
    }


    		
}
