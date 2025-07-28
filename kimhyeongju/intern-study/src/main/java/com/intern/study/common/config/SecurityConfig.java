package com.intern.study.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      http
      .csrf().disable()
      .authorizeHttpRequests()
              .requestMatchers(
                      "/h2-console/**",
                      "/swagger-ui/**",
                      "swagger-ui.html/**",
                      "/v3/api-docs/**").permitAll()
        .anyRequest().permitAll()
      .and()
      .formLogin().disable()
      .headers()
      .frameOptions().disable();

      return http.build();
    }
}

