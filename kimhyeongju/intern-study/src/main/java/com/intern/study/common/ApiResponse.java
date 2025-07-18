package com.intern.study.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;

@Getter
@Builder
@AllArgsConstructor
public class ApiResponse<T> {
	private String code;
	private String message;
	private T data;
}
