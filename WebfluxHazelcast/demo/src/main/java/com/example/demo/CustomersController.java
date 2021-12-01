package com.example.demo;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteCustomer(@PathVariable String id){
        customerRepo.deleteCustomer(id);
        // https://fullstackdeveloper.guru/2021/03/12/how-to-redirect-to-an-external-url-from-spring-boot-rest-controller/
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/")).build();
    }

}
