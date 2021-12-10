package com.example.demo;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Repository;

import io.r2dbc.spi.Connection;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import reactor.core.publisher.Mono;

@Repository
public class UserRepository {
    static ConnectionFactory connectionFactory = ConnectionFactories.get(
            "r2dbcs:mysql://root:@localhost:3306/springdinycon?");
    Mono<Connection> connectionMono = Mono.from(connectionFactory.create());

    // Creating a Mono using Project Reactor

    public Mono<UserDetails> findByUsername(String username) {
        return null;
    }
}
