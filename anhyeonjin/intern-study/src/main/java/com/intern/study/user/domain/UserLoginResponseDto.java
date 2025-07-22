package com.intern.study.user.domain;

import lombok.Data;

@Data
public class UserLoginResponseDto {
    private String userId;
    private String username;
    private String email;
    private String role;

    public UserLoginResponseDto(UserEntity user) {
        this.userId = user.getUserId();     
        this.username = user.getUsername(); 
        this.email = user.getEmail();
        this.role = user.getRole();
    }
}
