package com.intone.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.intone.auth.filter.BearerTokenAuthFilter;
 
@Configuration
@EnableWebSecurity
public class SecurityConfig {
 
    @Autowired
    BearerTokenAuthFilter bearerTokenAuthFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(request -> {
                request.requestMatchers("/auth/SignUp**", "/auth/SignIn**").permitAll();
                request.anyRequest().authenticated();
            })
            .csrf(AbstractHttpConfigurer::disable)
            .addFilterAfter(bearerTokenAuthFilter, UsernamePasswordAuthenticationFilter.class);
return http.build();
    }
}