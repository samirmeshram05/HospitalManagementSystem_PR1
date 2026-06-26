package com.hospital.config;


// ye package code ko bypass JWT ko karne ke liye banaya tha
/*
@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
            )
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }
	
	
}*/


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hospital.jwt.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http

                // Disable CSRF
                .csrf(csrf -> csrf.disable())

                // Stateless Session
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Authorization Rules
                .authorizeHttpRequests(auth -> auth

                        // Public APIs
                        .requestMatchers(
                                "/api/auth/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**")
                        .permitAll()

                        // Admin Only
                        .requestMatchers("/api/departments/**")
                        .hasRole("ADMIN")

                        // Admin + Doctor
                        .requestMatchers(
                                "/api/doctors/**",
                                "/api/prescriptions/**",
                                "/api/medicines/**")
                        .hasAnyRole("ADMIN", "DOCTOR")

                        // Admin + Receptionist
                        .requestMatchers(
                                "/api/patients/**",
                                "/api/bills/**")
                        .hasAnyRole("ADMIN", "RECEPTIONIST")

                        // Admin + Doctor + Receptionist
                        .requestMatchers("/api/appointments/**")
                        .hasAnyRole(
                                "ADMIN",
                                "DOCTOR",
                                "RECEPTIONIST")

                        // Everything else
                        .anyRequest()
                        .authenticated())

                .httpBasic(Customizer.withDefaults());

        // JWT Filter
        http.addFilterBefore(
                jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration)
            throws Exception {

        return configuration.getAuthenticationManager();

    }

}
