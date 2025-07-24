package com.intern.study.user.domain;

import lombok.Data;

@Data
public class UserLoginRequestDto {
	private String userId;
	private String password;
}
