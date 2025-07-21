package com.intern.study.user.domain;

import lombok.Data;

@Data
public class UserLoginResponseDto {
	
	private String userId;
	private String username;
	private String role;
	private String email;
	
	public UserLoginResponseDto(UserEntity user) {
		this.userId = user.getUserId();
		this.username = user.getUsername();
		this.role = user.getRole();
		this.email = user.getEmail();
		
	}

}
