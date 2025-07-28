package com.intern.study.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(name = "공통 Response")
public class ApiResponse<T> {

	@Schema(title = "상태", requiredMode = Schema.RequiredMode.REQUIRED, description = "성공 : SUCCESS, 실패 : FAIL",
	allowableValues = {"SUCCESS", "FAIL"})
	private String code;
	@Schema(title = "메시지")
	private String message;
	@Schema(title = "데이터")
	private T data;
}
