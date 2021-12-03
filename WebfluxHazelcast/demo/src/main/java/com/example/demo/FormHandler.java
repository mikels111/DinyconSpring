package com.example.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Component
public class FormHandler {
    ApplicationContext ctx = new ClassPathXmlApplicationContext();
    Resource template = ctx.getResource("classpath:addCustomerContent.html");

    // String formS=new String("<form action='/form' method='post' modelAttribute='customer'><table><tr><td><p>Id: <input type='text' id='id'></p></td></tr><tr><td><p>Name: <input type='text' id='name'></p></td></tr><tr><td><input type='submit' value='Submit' /></td></tr></table></form>"); 
    
    public Mono<ServerResponse> sampleForm(ServerRequest request) {
        return ServerResponse.ok().render("index",template);
    }

    public Mono<ServerResponse> displayFormData(ServerRequest request) {
        System.out.println("-------------------->"+request.formData());
        
        // Mono<MultiValueMap<String, String>> formData = request.formData();
        
        // BodyExtractor based. It didn't result any value for our program
        // It looks any earlier piece of code (Filter ?) already accessed the body
        // making it empty.
        
        Mono<MultiValueMap<String, String>> formData = request.body(BodyExtractors.toFormData());        
// return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/")).build();
        return ServerResponse.ok().render("index", formDataToCustomer(formData));
    }

    private Customer formDataToCustomer(Mono<MultiValueMap<String, String>> formData) {

        Customer customer = new Customer();

        formData.subscribe(formDatamap -> {
            customer.setId(formDatamap.getFirst("id"));
            customer.setName(formDatamap.getFirst("name"));
        });

        return customer;
    }
}
