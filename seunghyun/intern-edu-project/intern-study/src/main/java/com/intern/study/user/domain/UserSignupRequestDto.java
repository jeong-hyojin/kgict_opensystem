package com.intern.study.user.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;

@Data
@Schema(name = "사용자 회원가입 Request")
public class UserSignupRequestDto {

	@Schema(title = "사용자 ID", maxLength = 255, requiredMode = Schema.RequiredMode.REQUIRED)
	private String userId;

	@Schema(title = "비밀번호", maxLength = 255, requiredMode = Schema.RequiredMode.REQUIRED)
	private String password;

	@Schema(title = "사용자명", maxLength = 255, requiredMode = Schema.RequiredMode.REQUIRED)
	private String username;

	@Schema(title = "이메일", maxLength = 255, requiredMode = Schema.RequiredMode.REQUIRED)
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
