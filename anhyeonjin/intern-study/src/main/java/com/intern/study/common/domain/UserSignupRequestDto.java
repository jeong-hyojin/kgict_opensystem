package com.intern.study.common.domain;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.intern.study.user.domain.UserEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "사용자 회원가입 Request")
public class UserSignupRequestDto {

    @Schema(title = "사용자 ID", maxLength = 255, requiredMode = Schema.RequiredMode.REQUIRED, example = "testuser01")
    private String userId;

    @Schema(title = "사용자 이름", maxLength = 255, requiredMode = Schema.RequiredMode.REQUIRED, example = "홍길동")
    private String username;

    @Schema(title = "이메일", maxLength = 255, requiredMode = Schema.RequiredMode.REQUIRED, example = "test@example.com")
    private String email;

    @Schema(title = "비밀번호", maxLength = 255, requiredMode = Schema.RequiredMode.REQUIRED, example = "password123!")
    private String password;

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
