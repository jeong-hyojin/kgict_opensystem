package com.intern.study.user.controller;

import java.util.Map;

import com.intern.study.user.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.intern.study.common.domain.ApiResponse;
import com.intern.study.common.domain.UserSignupRequestDto;
import com.intern.study.user.domain.UserLoginRequestDto;
import com.intern.study.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final UserService userService;

	@PostMapping("/signup-post-map")
	public Map<String, Object> signupPost(@RequestBody Map<String, String> params) {
		return userService.signupPost(params);
	}

	@PostMapping("/signup-post-dto")
	public ApiResponse<?> signup(@RequestBody UserSignupRequestDto requestDto) {
		return userService.signupTestWithCondition(requestDto);
	}

	@PostMapping("/signup-post-jpa")
	public ApiResponse<?> signupRequest(@RequestBody UserSignupRequestDto requestDto) {
		return userService.signupRequest(requestDto);
	}

	@PostMapping("/login")
	public ApiResponse<?> login(@RequestBody UserLoginRequestDto requestDto) {
		return userService.login(requestDto);
	}

	@GetMapping("/{userId}")
	public ApiResponse<?> userDetail(@PathVariable String userId) {
		return userService.userDetail(userId);
	}

	@PostMapping("/update-password")
	public ApiResponse<?> updatePassword(@RequestBody Map<String, String> body) {
		String userId = body.get("userId");
		String newPassword = body.get("newPassword");

		try {
			return userService.updatePassword(userId, newPassword);
		} catch (Exception e) {
			log.error("비밀번호 변경 중 예외 발생", e);
			return new ApiResponse<>("FAIL", "비밀번호 변경 중 오류가 발생했습니다.", null);
		}
	}

}