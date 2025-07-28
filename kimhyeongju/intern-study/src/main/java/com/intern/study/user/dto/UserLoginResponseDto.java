package com.intern.study.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Schema(name = "사용자 로그인 Response")
public class UserLoginResponseDto {
    @Schema(title = "사용자 ID")
    private String userId;
	@Schema(title = "사용자명")
    private String username;
	@Schema(title = "권한")
    private String role;

    public UserLoginResponseDto(String userId, String username, String role) {
        this.userId = userId;
        this.username = username;
        this.role = role;
    }
}
