package com.intern.study.user.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name ="사용자 로그인 Response")
public class UserLoginResponseDto {

	@Schema(title = "사용자 ID")
	private String userId;

	@Schema(title = "사용자명")
	private String username;

	@Schema(title="권한")
	private String role;

	@Schema(title = "이메일")
	private String email;
	
	public UserLoginResponseDto(UserEntity user) {
		this.userId = user.getUserId();
		this.username = user.getUsername();
		this.role = user.getRole();
		this.email = user.getEmail();
		
	}

}
