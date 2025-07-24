package com.intern.study.admin.dto;

import com.intern.study.user.domain.UserEntity;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Data
public class AdminUserAddRequestDto {
    private String userId;
    private String password;
    private String username;
    private String email;

    public void encodeTempPassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.userId+"!");
    }

    public UserEntity toEntity(){
        return UserEntity.builder()
                .userId(this.userId)
                .password(this.password)
                .username(this.username)
                .email(this.email)
                .role("TEMP")
                .isActive(false)
                .regDate(LocalDateTime.now())
                .build();
    }

}
