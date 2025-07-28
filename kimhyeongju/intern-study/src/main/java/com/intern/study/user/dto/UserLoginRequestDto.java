package com.intern.study.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "사용자 로그인 Request")
public class UserLoginRequestDto {
	@Schema(title = "사용자 ID", maxLength = 255, requiredMode = Schema.RequiredMode.REQUIRED)
	private String userId;
	@Schema(title = "비밀번호", maxLength = 255, requiredMode = Schema.RequiredMode.REQUIRED)
	private String password;
}
