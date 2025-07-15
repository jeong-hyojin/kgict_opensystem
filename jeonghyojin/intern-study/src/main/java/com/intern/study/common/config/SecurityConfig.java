package com.intern.study.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	  http
      .csrf().disable()
      .authorizeHttpRequests()
        .anyRequest().permitAll() // ✅ 모든 요청 인증 없이 허용
      .and()
      .formLogin().disable(); // ✅ 로그인 폼 자체도 비활성화

	  return http.build();
  }
}