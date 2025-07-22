package com.intern.study.user.dto;

import com.intern.study.user.domain.UserEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserLoginResponseDto {
	String userId;
	String username;
	String email;
	String role;

	public static UserLoginResponseDto from(UserEntity user) {
		return new UserLoginResponseDto(
			user.getUserId(),
			user.getUsername(),
			user.getEmail(),
			user.getRole()
		);
	}
}
