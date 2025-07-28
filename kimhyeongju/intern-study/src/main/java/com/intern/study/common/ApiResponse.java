package com.intern.study.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@Schema(name = "공통 Response")
public class ApiResponse<T> {
    @Schema(
            title = "상태",
            requiredMode = Schema.RequiredMode.REQUIRED,
            description = "성공 : SUCCESS, 실패 : FAIL",
            allowableValues = {
                    "SUCCESS", "FAIL"
            }
    )
    private String code;

    @Schema(name = "alert 유무")
    private boolean alertEnabled;
    @Schema(name = "메세지")
    private String message;
    @Schema(name = "데이터")
    private T data;

    // ✅ static 제네릭 메서드
    public static <T> ApiResponse<T> success(T data, boolean alertEnabled) {
        return new ApiResponse<>("SUCCESS", alertEnabled, "성공", data);
    }

    public static <T> ApiResponse<T> fail(String message) {
        return new ApiResponse<>("FAIL", true, message, null);
    }

}
