package com.intern.study.common.domain;

import com.intern.study.user.domain.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Getter
@Setter
public class AdminUserAddRequestDto {
    private String userId;
    private String password;  // 자동으로 userId + "!" 로 설정됨
    private String username;
    private String email;

    public void encodeTempPassword(PasswordEncoder encoder) {
        this.password = encoder.encode(this.userId + "!");
    }

    public UserEntity toEntity() {
        return UserEntity.builder()
                .userId(userId)
                .password(password)
                .username(username)
                .email(email)
                .role("TEMP")             // 임시 사용자 권한
                .isActive(false)         // 비활성 상태
                .regDate(LocalDateTime.now())
                .build();
    }
}
