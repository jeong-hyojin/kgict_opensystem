package com.intern.study.user.dto;

import com.intern.study.user.domain.UserEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDetailResponseDto {
    String  userId;   // 아이디
    String  username; // 이름
    String  email;    // 이메일
    String  role;     // 권한
    Boolean isActive; // 사용여부

    public static UserDetailResponseDto from(UserEntity user) {
        return new UserDetailResponseDto(
              user.getUserId(),
              user.getUsername(),
              user.getEmail(),
              user.getRole(),
              user.getIsActive()
             );
    }
}
