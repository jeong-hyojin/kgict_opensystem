package com.intern.study.admin.domain;

import com.intern.study.user.domain.UserEntity;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Data
public class AdminUpdateRequestDto {
    private String userId;
    private String password;
    private String username;
    private String email;
    private String phone;
    private String role;
    private Boolean isActive;

    public void encodeTempPassword(PasswordEncoder passwordEncoder){
        //user_list.html의 사용자 추가 시, 비밀번호 입력이 없어서 임의로 1111 암호화
        this.password = passwordEncoder.encode("1111");
    }

    public UserEntity toEntity(){
        return UserEntity.builder()
                .userId(this.userId)
                .password(this.password)
                .username(this.username)
                .email(this.email)
                .phone(this.phone)
                .role(this.role)
                .isActive(isActive)
                .regDate(LocalDateTime.now())
                .build();
    }

}
