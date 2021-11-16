package com.example.demo;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import reactor.core.publisher.Mono;


public class MyUserDetails implements UserDetails {
    private Mono<User> user;
    private String username;
    public MyUserDetails(Mono<User> user) {
        this.user=user;
    }
    @Override
    public Collection getAuthorities() {
        return List.of(new SimpleGrantedAuthority("admin"));
    }
    @Override
    public String getPassword() {
        return "pass";
    }
 
    @Override
    public String getUsername() {
        return username;
    }
 
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
 
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
 
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
 
    @Override
    public boolean isEnabled() {
        return true;
    }
}
