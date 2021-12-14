package com.example.demo;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Repository;

import io.r2dbc.spi.Connection;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

@Repository
public interface UserRepository extends ReactiveCrudRepository<MyUser,Integer>{
    // static ConnectionFactory connectionFactory = ConnectionFactories.get(
    //         "r2dbcs:mysql://root:@localhost:3306/springdinycon?");
    // Mono<Connection> connectionMono = Mono.from(connectionFactory.create());

    // Creating a Mono using Project Reactor

    public Mono<MyUser> findByUsername(String username);
}
