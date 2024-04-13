package com.restapi.practice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.restapi.practice.service.JwtService;

/**
 * @author Jacinto Escobar Quezada
 * @version 1.0.0
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private JwtService jwtService;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http -> {
                    http.requestMatchers(HttpMethod.GET, "/health-check/**", "/auth/**").permitAll();
                    http.requestMatchers(HttpMethod.GET, "/character/**").authenticated();
                    http.anyRequest().denyAll();
                })
                .addFilterBefore(new JwtRequestFilter(jwtService), BasicAuthenticationFilter.class)
                .build();
    }
}
