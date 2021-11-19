package com.example.demo;

import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements ReactiveUserDetailsService {

    private final MyUserRepository myUserRepository;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return myUserRepository.findByUsername(username).cast(UserDetails.class);
    }

}
