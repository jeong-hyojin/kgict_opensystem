package com.intern.study.user.dto;

import java.time.LocalDateTime;

import com.intern.study.user.domain.UserEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserSignupRequestDto {
	String userId;
	String username;
	String email;
	String password;
	
	public void encodePassword(PasswordEncoder encoder) {
		this.password = encoder.encode(password);
	}
	
	public UserEntity toEntity() {
		return UserEntity.builder()
				.userId(userId)
				.username(username)
				.password(password)
				.email(email)
				.role("USER")
				.isActive(true)
				.regDate(LocalDateTime.now())
				.build();
	}
}
