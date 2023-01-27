package com.example.bookstoredemo.security;


import com.example.bookstoredemo.service.CustomerUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {
    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;


    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=
                new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customerUserDetailsService);
        authenticationProvider
                .setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }


    @Bean
    public SecurityFilterChain
    securityFilterChain(HttpSecurity http)throws Throwable{
        http
                .authorizeHttpRequests()
                .requestMatchers("/bootstrap/**","/images/**","/css-custom/**","/user/**","/cart/**","/customer/**").permitAll()
                .requestMatchers("/","/home/**").permitAll()
                .requestMatchers("/admin/**").hasRole("ROLE_ADMIN")
                .anyRequest()
                .authenticated();

        http.formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home")
                .failureUrl("/login-error")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/home")
                .and()
                .csrf()
                .disable();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
