package com.intern.study.user.controller;

import java.util.HashMap;
import java.util.Map;

import com.intern.study.admin.dto.UserPasswordUpdateRequestDto;
import com.intern.study.user.dto.UserDetailResponseDto;
import com.intern.study.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import com.intern.study.common.ApiResponse;
import com.intern.study.user.domain.UserEntity;
import com.intern.study.user.dto.UserLoginRequestDto;
import com.intern.study.user.dto.UserLoginResponseDto;
import com.intern.study.user.dto.UserSignupRequestDto;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
	UserService userService;

	// 요청 시 문자열 리턴
	@GetMapping("/signup-url")
	public String signupUrl(  @RequestParam String username
													, @RequestParam String email
													, @RequestParam String password ) {
		return "가입 요청 확인: " + username + " / " + email + " / " + password;
	}

	// fetch 테스트용
	@GetMapping("/signup-fetch")
	public Map<String, Object> signupFetch( @RequestParam String username
																				, @RequestParam String email
																				, @RequestParam String password ) {
		Map<String, Object> data = new HashMap<>();
		data.put("아이디",   username);
		data.put("이메일",   email);
		data.put("비밀번호", password);
		
		Map<String, Object> response = new HashMap<>();
		// 성공 / 실패 테스트용
		String code = username.equals("success") ? "SUCCESS" : "FAIL";
		response.put("code", code);
		response.put("data", data);
		
		return response;
	}

	// post 가입 요청 Map 반환
	@PostMapping("/signup-post-map")
	public Map<String, Object> signupPost(@RequestBody Map<String, String> params) {
		String username = params.get("username");
		String email    = params.get("email");
		
		// 데이터 구성
		Map<String, Object> data = new HashMap<>();
		data.put("아이디", username);
		data.put("이메일", email);
		
		// 응답 처리
		Map<String, Object> response = new HashMap<>();
		if("success".equals(username)) {
			response.put("code",    "SUCCESS");
			response.put("message", "회원가입이 완료되었습니다.");
		} else {
			response.put("code",   "FAIL");
			response.put("message", username + "는 이미 등록된 아이디입니다.");
		}
		
		response.put("data", data);
		
		return response;
	}

	// post 가입 요청 dto 반환
	@PostMapping("/signup-post-dto")
	public ApiResponse<?> signupPost(@RequestBody UserSignupRequestDto requestDto) {
		// 클라이언트에서 받은 JSON -> DTO 자동 매핑됨
		Map<String, Object> response = new HashMap<>();
				
		String username = requestDto.getUsername();
//		String email    = requestDto.getEmail();
//		String password = requestDto.getPassword();

	
		// 회원가입 서비스 호출 (아이디 중복 검사, 비밀번호 초기화, DB 저장 등)
		
		// 테스트용 : 아이디가 "success"면 성공, 아니면 실패
		response.put("username", username);
		
		if("success".equals(username)) {
			String code    = "SUCCESS";
			String message = "회원가입을 완료하였습니다.";
			
			return new ApiResponse<>(code, message, response);
		} else {
			String code    = "FAIL";
			String message = username + "는 이미 등록된 아이디입니다.";
			
			return new ApiResponse<>(code, message, response); // 실패지만 메세지는 포함됨
		}
	}

	// post 가입요청 JPA (표준)
	@PostMapping("/signup-post-jpa")
	public ApiResponse<?> signupRequest(@RequestBody UserSignupRequestDto requestDto) {
		try {
			String userId = userService.signupRequest(requestDto);
			return new ApiResponse<>("SUCCESS", "회원가입에 성공하였습니다.", userId);
		} catch(Exception e) {
			log.error("회원가입 중 예외 발생", e);
			return new ApiResponse<>("FAIL", e.getMessage(), null);
		}
	}

	// 로그인
	@PostMapping("/login")
	public ApiResponse<?> loginRequest(@RequestBody UserLoginRequestDto request) {
		try {
			UserLoginResponseDto dto = userService.login(request.getUserId(), request.getPassword());
			return new ApiResponse<>("SUCCESS", "로그인 성공", dto);
		} catch(Exception e) {
			log.error("로그인 중 예외 발생", e);
			return new ApiResponse<>("FAIL", e.getMessage(), null);
		}
	}

	// 사용자 정보 조회
	@GetMapping("/{userId}")
	public ApiResponse<?> userDetail(@PathVariable String userId) {
		try {
			UserEntity user = userService.userDetail(userId);
			return new ApiResponse<>("SUCCESS", "조회 성공", UserDetailResponseDto.from(user));
		} catch(Exception e) {
			log.error("사용자 조회 중 예외 발생", e);
			return new ApiResponse<>("FAIL", e.getMessage(), null);
		}
	}

	// 임시 사용자 비밀번호 변경
	@PutMapping("/user-password-update")
	public ApiResponse<?> updatePassword(@RequestBody UserPasswordUpdateRequestDto requestDto) {
		try {
			String userId = userService.updatePassword(requestDto);
			return new ApiResponse<>("SUCCESS", "사용자 비밀번호 변경에 성공했습니다. 재로그인 해주세요.", userId);
		} catch (Exception e) {
			return new ApiResponse<>("FAIL", e.getMessage(), null);
		}
	}
}
