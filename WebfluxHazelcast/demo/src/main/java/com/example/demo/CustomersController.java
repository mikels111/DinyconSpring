package com.example.demo;

import java.net.URI;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customers")
public class CustomersController {
    ApplicationContext ctx = new ClassPathXmlApplicationContext();
    Resource template = ctx.getResource("classpath:index.html");

    private final CustomerRepository customerRepo;

    public CustomersController(CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    @GetMapping("/{id}")
    private Mono<Customer> getCustomerById(@PathVariable String id) {
        return customerRepo.findCustomerById(id);
    }

    @GetMapping
    private Flux<Customer> getAllCustomers() {
        return customerRepo.findAllCustomers();
    }

    @PostMapping("/{id}/{name}")
    private Flux<Customer> saveCustomer(@PathVariable String id, @PathVariable String name) {
        System.out.println("-------------------------->"+id + name);
        return customerRepo.putCustomer(id, name);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteCustomer(@PathVariable String id) {
        customerRepo.deleteCustomer(id);
        // https://fullstackdeveloper.guru/2021/03/12/how-to-redirect-to-an-external-url-from-spring-boot-rest-controller/
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/")).build();
    }

    @PostMapping("/formSaveCustomer")
    public ResponseEntity<Void> processForm(@ModelAttribute("customer") Customer customer) {
        if(customer.getId().length()!=0 && customer.getName().length()!=0){
            customerRepo.putCustomer(customer.getId(), customer.getName());
        }
        
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/")).build();
    }

    // @GetMapping("/formSaveCustomer")
    // private Mono<ServerResponse> getForm(Model model) {
    //     model.addAttribute("content", "../fragments/addCustomersContent.html");
    //     return ServerResponse.ok().render("index",template);
    // }

}
