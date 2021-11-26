package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import reactor.core.publisher.Mono;

@Controller
public class HomeController {
    @GetMapping("/")
    public Mono<String> index(Model model) {
        model.addAttribute("nombre","Inicio");
		model.addAttribute("content","");
		return Mono.just("index");
	}
}
