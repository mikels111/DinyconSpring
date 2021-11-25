// package com.example.demo;

// import org.springframework.http.HttpHeaders;
// import org.springframework.http.MediaType;
// import org.springframework.web.reactive.function.client.WebClient;

// import reactor.core.publisher.Flux;
// import reactor.core.publisher.Mono;

// public class EmployeeWebClient {

//      WebClient webClient;
//     // WebClient webClient = WebClient.builder().baseUrl("http://localhost:8080")
//     //         .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();

//     public EmployeeWebClient(WebClient webClient){
//         this.webClient=webClient;
//     }

//     public Flux<Employee> findAll() {
//         return webClient.get()
//         .uri("/employees")
//         .retrieve()
//         .bodyToFlux(Employee.class);
//     }

//     // public void consume() {

//     //     Mono<Employee> employeeMono = client.get().uri("/employees/{id}", "1").retrieve().bodyToMono(Employee.class);

//     //     employeeMono.subscribe(System.out::println);

//     //     Flux<Employee> employeeFlux = client.get().uri("/employees").retrieve().bodyToFlux(Employee.class);

//     //     employeeFlux.subscribe(System.out::println);

//     //     Flux<Grupo> gruposFlux = client.get().uri("/grupos").retrieve().bodyToFlux(Grupo.class);
//     //     gruposFlux.subscribe(System.out::println);
//     // }
// }