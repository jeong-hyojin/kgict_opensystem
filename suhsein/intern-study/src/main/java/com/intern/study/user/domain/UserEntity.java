package com.intern.study.user.domain;

import java.time.LocalDateTime;

import com.intern.study.admin.dto.UserPasswordUpdateRequestDto;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
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

	public void changePassword(PasswordEncoder encoder, UserPasswordUpdateRequestDto requestDto) {
		if(!encoder.matches(requestDto.getOldPassword(), this.password)){
			throw new IllegalArgumentException("기존 비밀번호가 일치하지 않습니다.");
		}
		this.password = encoder.encode(requestDto.getNewPassword());
		if("TEMP".equals(this.role)){
			this.role = "USER";
			this.isActive = true;
		}
	}
}
