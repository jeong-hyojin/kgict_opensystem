package com.intern.study.user.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(name = "사용자 Response")
public class UserResponseDto {

    @Schema(title = "사용자 ID")
    private String userId;
    
    @Schema(title = "비밀번호")
    private String password;
    
    @Schema(title = "사용자명")
    private String username;
    
    @Schema(title = "이메일")
    private String email;
    
    @Schema(title = "전화번호")
    private String phone;
    
    @Schema(title = "권한", allowableValues = {"USER", "ADMIN", "TEMP"})
    private String role;
    
    @Schema(title = "활성화 여부")
    private Boolean isActive;

    @Schema(title = "등록일시")
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
