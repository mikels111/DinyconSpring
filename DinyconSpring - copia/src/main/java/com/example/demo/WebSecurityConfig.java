package com.example.demo;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;

	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
		.antMatchers("/home").hasRole("admin")
		.antMatchers("/").permitAll().and()
		.formLogin();
    }

	// @Bean(name = "dataSource")
    // public DriverManagerDataSource dataSource() {
    //     DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
    //     driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
    //     driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306");
	// 	driverManagerDataSource.setSchema("springdinycon");
    //     driverManagerDataSource.setUsername("root");
    //     driverManagerDataSource.setPassword("mikelS1234");
    //     return driverManagerDataSource;
    // }
 
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
			.dataSource(dataSource)
			.passwordEncoder(PasswordEncoder())
            .usersByUsernameQuery("select username,password,enabled from users where username=?")
			.authoritiesByUsernameQuery(
                "SELECT u.username, p.permission " +
                "FROM permission p, users u " +
                "WHERE u.username = ? " +
                "AND u.idusuario = p.user_id"
				);
    }

	@Bean
	public PasswordEncoder PasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// @Bean
	// @Override
	// public UserDetailsService userDetailsService() {
	// UserDetails user =
	// User.withDefaultPasswordEncoder()
	// .username("user")
	// .password("password")
	// .roles("USER")
	// .build();

	// return new InMemoryUserDetailsManager(user);
	// }
}
