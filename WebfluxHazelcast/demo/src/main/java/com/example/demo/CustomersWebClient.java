package com.example.demo;

import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;

public class CustomersWebClient {
    private static final WebClient client = WebClient.create("http://localhost:8081");

    public static Flux<Customer> getAllCustomers(){
        Flux<Customer> customers = client.get()
                .uri("/customers")
                .retrieve()
                .bodyToFlux(Customer.class);
        return customers;
    }
    // public static Flux<Customer> deleteCustomer(long id){
    //     Flux<Customer> customers = client.delete()
    //             .uri("/customers/"+id)
    //             .retrieve()
    //             .bodyToFlux(Customer.class);
    //     return customers;
    // }
    
}
