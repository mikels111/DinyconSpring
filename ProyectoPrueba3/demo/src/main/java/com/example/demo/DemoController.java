package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class DemoController {
    @GetMapping("/")
    public String index(Model model) {
		model.addAttribute("nombre","Inicio");
		model.addAttribute("content","");
        return "index";
    }

    @GetMapping("/admin")
    public String greetings(Model model) {
        model.addAttribute("nombre","Admin");
        model.addAttribute("content","../fragments/greetingsContent");
        return "index";
    }

    @GetMapping("/logout")
    public String iniciarSesion(Model model) {
        model.addAttribute("nombre","Cerrar sesión");
        model.addAttribute("content","../fragments/logoutContent");
        return "index";
    }
    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("nombre","About");
        model.addAttribute("content","../fragments/aboutContent");
        return "index";
    }

    // @GetMapping("/error")
    // public String error(Model model) {
    //     model.addAttribute("nombre","Error");
    //     model.addAttribute("content","../fragments/errorContent");
    //     return "index";
    // }

    
}