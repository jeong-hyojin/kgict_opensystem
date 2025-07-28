package com.intern.study.user.dto;

import lombok.Data;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@ToString
public class ChangePasswordRequestDto {
    String userId;
    String newPassword;

    public void encodePassword(PasswordEncoder encoder) {
        this.newPassword = encoder.encode(this.newPassword);
    }
}

