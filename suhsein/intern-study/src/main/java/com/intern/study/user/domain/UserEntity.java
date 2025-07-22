package com.intern.study.user.domain;

import java.time.LocalDateTime;

import com.intern.study.user.dto.UserSignupRequestDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	String userId;
	String password;
	String username;
	String email;
	String phone;
	String role;
	Boolean isActive;
	LocalDateTime regDate;

	public static UserEntity from(UserSignupRequestDto dto) {
		return UserEntity.builder()
					.userId(dto.getUserId())
    			.password(dto.getPassword())
    			.username(dto.getUsername())
    			.email(dto.getEmail())
    			.role("USER")
    			.isActive(true)
    			.regDate(LocalDateTime.now())
    			.build();
	}
}
