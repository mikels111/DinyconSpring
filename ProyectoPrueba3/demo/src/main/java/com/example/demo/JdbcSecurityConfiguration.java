package com.example.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.r2dbc.spi.Connection;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import reactor.core.publisher.Mono;

// @EnableWebSecurity
@EnableWebFluxSecurity
public class JdbcSecurityConfiguration extends WebSecurityConfigurerAdapter {

    // @Bean
    // public DataSource dataSource() {
    // return DataSourceBuilder.create().driverClassName("com.mysql.jdbc.Driver")
    // .url("jdbc:mysql://localhost:3306/springdinycon").username("root").password("mikelS1234").build();
    // }

    // @Override
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception
    // {
    // auth.jdbcAuthentication().dataSource(dataSource()).passwordEncoder(new
    // BCryptPasswordEncoder())
    // .usersByUsernameQuery("SELECT username, password, enabled from users where
    // username = ?")
    // .authoritiesByUsernameQuery("select username, role from users where
    // username=?");
    // }

    // ConnectionFactory connectionFactory = ConnectionFactories
    //         .get("r2dbcs:mysql://root:mikelS1234@127.0.0.1:3306/springdinycon");
    // Mono<Connection> connectionMono = Mono.from(connectionFactory.create());

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/**").authorizeRequests().antMatchers("/admin").hasRole("ADMIN").antMatchers("/about")
                .permitAll().and().formLogin().permitAll().and().logout().permitAll();
    }

    @Bean
    ReactiveAuthenticationManager authenticationManager(MyUserDetailsService myUserDetailsService) {
        return new UserDetailsRepositoryReactiveAuthenticationManager(myUserDetailsService);
    }
}