package com.intern.study.user.dto;

import lombok.Data;

@Data
public class UserLoginResponseDto {
	private String userId;
	private String username;
	private String email;

	public UserLoginResponseDto(String userId, String username, String email) {
		this.userId = userId;
		this.username = username;
		this.email = email;
	}
}
