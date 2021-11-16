package com.example.dinycon_spring2;

import java.sql.Connection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@EnableWebSecurity
public class DinyconSpring2Config<R2dbcEntityTemplate> extends WebSecurityConfigurerAdapter {

    // @Bean
    // SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) throws
    // Exception {
    // return http.authorizeExchange().pathMatchers(HttpMethod.GET,
    // "/api/movie/**").hasRole("USER")
    // .pathMatchers(HttpMethod.POST,
    // "/api/movie/**").hasRole("ADMIN").anyExchange().authenticated().and()
    // .formLogin().and().build();
    // }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/home").hasAnyRole("USER").antMatchers("/greetings").hasRole("ADMIN")
                .and().formLogin();
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsRepository() {
        // https://stackoverflow.com/questions/49847791/java-spring-security-user-withdefaultpasswordencoder-is-deprecated/49847852
        UserDetails user = User.withUsername("user").password(passwordEncoder().encode("password")).roles("USER")
                .build();
        UserDetails admin = User.withUsername("admin").password(passwordEncoder().encode("admin123"))
                .roles("USER", "ADMIN").build();
        UserDetails user2 = User.withUsername("user2").password("1234").roles("USER").build();

        ConnectionFactory connectionFactory = ConnectionFactories
                .get("r2dbcs:mysql://root:mikelS1234@127.0.0.1:3306/r2dbc?");
                
        Mono<Connection> connectionMono = (Mono<Connection>) Mono.from(connectionFactory.create());

        R2dbcEntityTemplate template = new R2dbcEntityTemplate(connectionFactory);

        Flux<User> all = template.select(Person.class)
			.matching(query(where("firstname").is("John")
				.and("lastname").in("Doe", "White"))
			  .sort(by(desc("id"))))
			.all();

        return new MapReactiveUserDetailsService(user, admin, user2);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
