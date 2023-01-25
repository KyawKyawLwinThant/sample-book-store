package com.example.bookstoredemo.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain
    securityFilterChain(HttpSecurity http)throws Throwable{
        http
                .authorizeHttpRequests()
                .anyRequest()
                .permitAll();

        return http.build();
    }
}
