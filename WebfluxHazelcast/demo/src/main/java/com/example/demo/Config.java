package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses=UserService.class)
public class Config {
    // UserRepository userRepository;
    // @Bean
    // public UserService getUserService(){
        
    //     return new UserService(userRepository);
    // }
}
