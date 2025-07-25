package com.intern.study.admin.dto;

import com.intern.study.user.domain.UserEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminUserAddRequestDto {
    String userId;
    String password;
    String username;
    String email;

    public void encodeTempPassword(PasswordEncoder encoder) {
        this.password = encoder.encode(this.userId + "!");
    }

    public UserEntity toEntity() {
        return UserEntity.builder()
                .userId(userId)
                .password(password)
                .username(username)
                .email(email)
                .role("TEMP")
                .isActive(false)
                .regDate(LocalDateTime.now())
                .build();
    }
}
