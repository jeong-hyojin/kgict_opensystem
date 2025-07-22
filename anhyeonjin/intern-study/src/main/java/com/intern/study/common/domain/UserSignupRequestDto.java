package com.intern.study.common.domain;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.intern.study.user.domain.UserEntity;

import lombok.Data;

@Data
public class UserSignupRequestDto {
    private String userId;
    private String username;
    private String email;
    private String password;

    public void encodePassword(PasswordEncoder encoder) {
        this.password = encoder.encode(this.password); // 오타: String encoder.encode → encoder.encode만
    }

    public UserEntity toEntity() {
        return UserEntity.builder()
                .userId(userId)
                .password(password)                      // 오타: .password(password)email → 줄바꿈 및 순서 수정
                .username(username)                      // 오타: .username() → .username(username)
                .email(email)
                .role("USER")
                .isActive(true)                        // 오타: tryue → "true" (String 타입이라고 가정)
                .regDate(LocalDateTime.now())            // 오타: reDate → regDate 로 일치 가정
                .build();                                // 오타: .build)() → .build()
    }
}
