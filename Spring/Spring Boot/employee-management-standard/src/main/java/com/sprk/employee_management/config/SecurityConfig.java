package com.sprk.employee_management.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
//        http.csrf(csrf->csrf.disable())
//                .authorizeHttpRequests(auth->auth
//                        .requestMatchers("/public/security/hello").hasRole("ADMIN")
//                        .requestMatchers("/public/security/hi").hasRole("USER")
//                        .anyRequest().authenticated()).httpBasic(Customizer.withDefaults());
////                .formLogin(Customizer.withDefaults());
//        return http.build();
//    }
//    @Bean
//    public UserDetailsManager users(){
//        UserDetails user = User.builder().username("neo")
//                .password(passwordEncoder().encode("neo123")).roles("USER").build();
//        UserDetails admin = User.builder().username("darshan")
//                .password(passwordEncoder().encode("1234")).roles("ADMIN").build();
//        return new InMemoryUserDetailsManager(user,admin);
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//
//        return new BCryptPasswordEncoder();
//    }

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf->csrf.disable())
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/login").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}