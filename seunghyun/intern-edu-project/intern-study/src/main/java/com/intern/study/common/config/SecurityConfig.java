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
		http.csrf(csrf -> csrf.disable())
			.headers(headers -> headers.frameOptions().disable()) // ✅ H2 콘솔 iframe 허용
			.authorizeHttpRequests(auth -> auth
			.requestMatchers("/h2-console/**").permitAll() // ✅ 허용 경로 추가
	        .anyRequest().permitAll()
		);
	  	return http.build();
	}
}