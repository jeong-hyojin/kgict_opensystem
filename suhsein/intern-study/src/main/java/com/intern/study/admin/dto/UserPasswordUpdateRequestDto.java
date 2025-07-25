package com.intern.study.admin.dto;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserPasswordUpdateRequestDto {
    String userId;
    String oldPassword;
    String newPassword;
}
