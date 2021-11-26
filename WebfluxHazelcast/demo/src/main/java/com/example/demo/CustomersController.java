package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customers")
public class CustomersController {

    private final CustomerRepository customerRepo;

    public CustomersController(CustomerRepository customerRepo){
        this.customerRepo=customerRepo;
    }

    @GetMapping("/{id}")
    private Mono<Customer> getCustomerById(@PathVariable String id){
        return customerRepo.findCustomerById(id);
    }
    @GetMapping
    private Flux<Customer> getAllCustomers(){
        return customerRepo.findAllCustomers();
    }
    @PostMapping("/{id}/{name}")
    private Flux<Customer> saveCustomer(@PathVariable String id,@PathVariable String name){
        return customerRepo.putCustomer(id,name);
    }

}
