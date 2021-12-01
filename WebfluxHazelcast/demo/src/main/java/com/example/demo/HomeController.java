package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

@Controller
public class HomeController {
    @GetMapping("/")
    public Mono<String> index(Model model) {
        model.addAttribute("nombre", "Inicio");
        model.addAttribute("customers", CustomersWebClient.getAllCustomers());
        model.addAttribute("content", "../fragments/customersContent.html");
        // customers.subscribe(System.out::println);
        return Mono.just("index");
    }
    @GetMapping("/")
    public Mono<String> crearCustomer(Model model) {
        model.addAttribute("nombre", "Crear Customer");
        model.addAttribute("content", "../fragments/customersContent.html");
        // customers.subscribe(System.out::println);
        return Mono.just("index");
    }
}
