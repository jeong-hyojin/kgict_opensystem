package com.intern.study.user.domain;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;

@Data
public class UserSignupRequestDto {
	private String userId;
	private String password;
	private String username;
	private String email;

	
	public void encodePassword(PasswordEncoder encoder) {
		this.password = encoder.encode(this.password);
		
	}
	
	public UserEntity toEntity() {
		
		return UserEntity.builder()
				.userId(userId)
				.password(password)
				.username(username)
				.email(email)
				.role("USER")
				.isActive(true)
				.regDate(LocalDateTime.now())
				.build();
	}
	
}
