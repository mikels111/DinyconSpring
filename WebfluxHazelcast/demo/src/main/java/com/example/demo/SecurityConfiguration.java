package com.example.demo;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create().driverClassName("com.mysql.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/springdinycon").username("root").password("mikelS1234").build();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource()).passwordEncoder(new BCryptPasswordEncoder())
                .usersByUsernameQuery("SELECT username, password, enabled from users where username = ?")
                .authoritiesByUsernameQuery("select username, role from users where username=?");
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/**").authorizeRequests()
            .antMatchers("/admin").hasRole("ADMIN")
            .antMatchers("/about").permitAll().and()
            .formLogin().permitAll().and()
            .logout().permitAll();
    }
}
