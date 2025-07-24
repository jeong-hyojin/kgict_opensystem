package com.intern.study.user.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponseDto {

    private String userId;
    private String password;
    private String username;
    private String email;
    private String phone;
    private String role;
    private Boolean isActive;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd HH:mm")
    private LocalDateTime regDate;

    public UserResponseDto(UserEntity user) {
        this.userId = user.getUserId();
        this.password = user.getPassword();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.role = user.getRole();
        this.isActive = user.getIsActive();
        this.regDate = user.getRegDate();
    }
}
