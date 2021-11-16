package com.example.demo;

import reactor.core.publisher.Mono;

public interface UserRepository {
    public Mono<User> findByUsername(String username);
}
