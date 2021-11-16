package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class UserRepositoryImpl implements UserRepository{
    private Map<Long,User> users= new HashMap<Long, User>();
    @PostConstruct
    public void initIt() throws Exception{
        // users.put(Long.valueOf(1),new User(Long.valueOf(1),"admin","1234"));

    }
    @Override
    public Mono<User> findByUsername(String username){
        return Mono.just(users.get(username));
    }

}
