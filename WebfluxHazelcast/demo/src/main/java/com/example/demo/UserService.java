package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class UserService implements ReactiveUserDetailsService{
    
    
    private final UserRepository userRepository;

    // public UserService(UserRepository userRepository){
    //     this.userRepository=userRepository;
    // }

    // public UserService(UserRepository userRepository){
    //     this.userRepository=userRepository;
    // }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return userRepository.findByUsername(username).cast(UserDetails.class);
    }
}
