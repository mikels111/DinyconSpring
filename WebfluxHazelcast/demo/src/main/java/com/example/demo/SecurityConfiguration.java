package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.ReactiveAuthenticationManagerResolver;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfiguration{

    // @Autowired
    // private UserRepository userRepository;

    // @Autowired
    // private ReactiveAuthenticationManager authenticationManager;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        return http
                .csrf().disable()
                .authorizeExchange()
                .pathMatchers("/**")
                .authenticated().and()
                .httpBasic().and()
                .formLogin().and()
                .build();
    }

    // @Bean
    // public ReactiveUserDetailsService userDetailsService() {
    //     return (username) -> userRepository.findByUsername(username);
    // }
    @Bean
    ReactiveAuthenticationManager authenticationManager(UserService userService){
        return new UserDetailsRepositoryReactiveAuthenticationManager(userService);
    }

    

}
