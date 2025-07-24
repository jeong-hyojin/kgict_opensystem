package com.intern.study.user.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserLoginResponseDto {
	private String userId;
	private String username;
	private String role;

	public UserLoginResponseDto(String userId, String username, String role) {
		this.userId = userId;
		this.username = username;
		this.role = role;
	}
}
