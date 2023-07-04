package com.codewithraushan.springboot.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){

        UserDetails Ravi = User.builder()
                .username("Ravi")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();

        UserDetails Sumit = User.builder()
                .username("Sumit")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER")
                .build();


        UserDetails Raushan = User.builder()
                .username("Raushan")
                .password("{noop}test123")
                .roles("EMPLOYEE","MANAGER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(Ravi, Sumit, Raushan);

    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests(configure ->
                configure
                        .requestMatchers(HttpMethod.GET,"/api/employees").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET,"/api/employees/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST,"/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT,"/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE,"/api/employees/**").hasRole("ADMIN")
        );

        //use basic Authentication
        httpSecurity.httpBasic(Customizer.withDefaults());

        //disable Cross-Site Request Forgery (CSRF)
        //in general not request for stateless Rest apis that use POST, PUT, DELETE and/or PATCH
        httpSecurity.csrf(csrf -> csrf.disable());

        return httpSecurity.build();
    }
}
