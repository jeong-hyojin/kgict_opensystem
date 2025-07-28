package com.intern.study.user.controller;

import java.util.Map;

import com.intern.study.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "사용자", description = "사용자 인증, 가입, 정보 조회 등의 API")
public class UserController {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final UserService userService;

	@PostMapping("/signup-post-map")
	@Operation(summary = "회원가입(Map 기반)", description = "Map<String, String> 형태로 데이터를 받아 사용자를 등록합니다. 테스트용 엔드포인트입니다.")
	public Map<String, Object> signupPost(@RequestBody Map<String, String> params) {
		return userService.signupPost(params);
	}

	@PostMapping("/signup-post-dto")
	@Operation(summary = "회원가입(DTO, 조건검사 포함)", description = "UserSignupRequestDto를 받아 회원가입을 진행하며, 중복 검사나 유효성 조건을 포함합니다.")
	public ApiResponse<?> signup(@RequestBody UserSignupRequestDto requestDto) {
		return userService.signupTestWithCondition(requestDto);
	}

	@PostMapping("/signup-post-jpa")
	@Operation(summary = "회원가입(JPA 저장)", description = "UserSignupRequestDto를 기반으로 실제 JPA 저장까지 수행하는 정식 회원가입 API입니다.")
	public ApiResponse<?> signupRequest(@RequestBody UserSignupRequestDto requestDto) {
		return userService.signupRequest(requestDto);
	}

	@PostMapping("/login")
	@Operation(summary = "로그인", description = "사용자의 ID와 비밀번호를 받아 로그인 처리를 수행합니다. 성공 시 토큰 또는 사용자 정보를 반환합니다.")
	public ApiResponse<?> login(@RequestBody UserLoginRequestDto requestDto) {
		return userService.login(requestDto);
	}

	@GetMapping("/{userId}")
	@Operation(summary = "회원 상세 조회", description = "PathVariable로 전달된 userId에 해당하는 사용자의 상세 정보를 반환합니다.")
	public ApiResponse<?> userDetail(@PathVariable String userId) {
		return userService.userDetail(userId);
	}

	@PostMapping("/update-password")
	@Operation(summary = "비밀번호 변경", description = "userId와 newPassword를 입력받아 해당 사용자의 비밀번호를 암호화하여 변경합니다.")
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