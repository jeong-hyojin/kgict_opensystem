package com.intern.study.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ApiResponse<T> {
    private String code;
    private boolean alertEnabled;
    private String message;
    private T data;

    // ✅ static 제네릭 메서드
    public static <T> ApiResponse<T> success(T data, boolean alertEnabled) {
        return new ApiResponse<>("SUCCESS", alertEnabled, "성공", data);
    }

    public static <T> ApiResponse<T> fail(String message) {
        return new ApiResponse<>("FAIL", true, message, null);
    }

}
