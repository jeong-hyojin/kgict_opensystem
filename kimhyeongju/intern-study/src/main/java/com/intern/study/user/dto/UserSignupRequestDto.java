package com.intern.study.user.dto;

import com.intern.study.user.domain.UserEntity;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Data
public class UserSignupRequestDto {
	private String userId;
	private String username;
	private String email;
	private String password;

	public void encodePassword(PasswordEncoder encoder) {
		this.password = encoder.encode(this.password);
	}

	public UserEntity toEntity() {
		return UserEntity.builder()
				.userId(this.userId)
				.username(this.username)
				.password(this.password)
				.email(this.email)
				.role("USER")
				.isActive(true)
				.regDate(LocalDateTime.now())
				.build();
	}
}
