package com.codewithraushan.springboot.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

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


        UserDetails Ayan = User.builder()
                .username("Ayan")
                .password("{noop}test123")
                .roles("EMPLOYEE","MANAGER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(Ravi, Sumit, Ayan);

    }
}
