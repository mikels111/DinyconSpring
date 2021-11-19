package com.example.demo;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;

@Repository
public interface MyUserRepository extends ReactiveCrudRepository<MyUser,Integer> {
    @Query("SELECT username, password, enabled from users where username = :username")
    Mono<MyUser> findByUsername(String username);
}
