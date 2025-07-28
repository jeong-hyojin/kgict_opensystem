package com.intern.study.user.controller;

import java.util.HashMap;
import java.util.Map;

import com.intern.study.common.ApiResponse;
import com.intern.study.user.domain.*;

import com.intern.study.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RequiredArgsConstructor
@RestController
@Tag(name = "사용자")
@Validated
@RequestMapping("/api/user")
public class UserController {

	private final UserService userService;

    @GetMapping("/signup-url")
	@Operation(summary = "[Test] 가입 요청 확인")
    public String signupUrl(@RequestParam String username,
                            @RequestParam String email,
                            @RequestParam String password) {

        return "가입 요청 확인 : username=" + username + ", email=" + email;
 
    }


    @GetMapping("/signup-fetch")
	@Operation(summary = "[Test] 회원가입 Fetch")
    public Map<String, Object> signupFetch(	@RequestParam String username,
                            				@RequestParam String email,
                            				@RequestParam String password) {
    	
    	
    	Map<String, Object> data = new HashMap<>();
    	data.put("아이디", username);
    	data.put("이메일", email);
    	data.put("비밀번호", password);
    	
    	Map<String, Object> response = new HashMap<>();
    	
    	//성공 실패 확인용
    	String code = username.equals("success")? "SUCCESS" : "Fail";
    	response.put("code", code);
    	response.put("data", data);
    	
    	return response;
    }
    
    
    @PostMapping("/signup-post-map")
	@Operation(summary = "[Test] 회원가입 Map 사용")
    public Map<String, Object> signupPost(@RequestBody Map<String, String> params){
    	
    	
    	String username = params.get("username");
    	String email = params.get("email");
    	
    	//데이터 구성
    	Map<String, Object> data = new HashMap<>();
    	data.put("아이디", username);
    	data.put("이메일", email);
    	
    	//응답처리
    	Map<String, Object> response = new HashMap<>();
    	
    	if("success".equals(username)) {
    		
    		response.put("code", "SUCCESS");
    		response.put("message", "회원가입이 완료됐습니다.");
    		
    	}else {
    		response.put("code", "FAIL");
    		response.put("message", username + "은 이미 등록된 아이디입니다.");
    	}
    	response.put("data", data);
    	
    	return response;    		
    }
    
    @PostMapping("/signup-post-dto")
	@Operation(summary = "[Test] 회원가입 로직만 구현")
    public ApiResponse<?> signup(@RequestBody UserSignupRequestDto resquestDto){
    	
    	//클라이언트에서 받은 json ->dto 자동 매핑
    	Map<String, Object> response = new HashMap<>();
    	
    	String username = resquestDto.getUsername();
    	String email = resquestDto.getEmail();
    	
    	//회원가입 서비스 호출(아이디 중복 검사, 비밀번호 초기화, DB저장 등 )
    	
    	//테스트용 : 아이디가 success면 성공 아니면 실패
    	if( "success".equals(username)) {
    		String code= "SUCCESS";
    		String message ="회원가입이 완료됐습니다.";
    		response.put("username", username);
    		return new ApiResponse<>(code, message, response);
    	}else{
    		String code = "FAIL";
    		String message = username + "이미 등록된 아이디입니다.";
    		response.put("username", username);
    		return new ApiResponse<>(code, message, response); // 실패지만 메시지는 포함됨
    	}
    }

/* 리팩토링 후, Controller-Service-Repository 분리 */
    @PostMapping("/signup-post-jpa")
	@Operation(summary = "회원가입")
    public ApiResponse<?> signupRequest(@RequestBody UserSignupRequestDto resquestDto){

		try {
			Long id = userService.signupRequest(resquestDto);
			return new ApiResponse<>("SUCCESS","회원가입 성공", id);

		} catch (Exception e) {
			log.error("회원가입 오류", e);
			return new ApiResponse<>("FAIL", "회원가입 중 에러가 발생했습니다.", null);
		}
    }    
    
    
    @PostMapping("/login")
	@Operation(summary = "로그인")
    public ApiResponse<?> login(@RequestBody UserLoginRequestDto requestDto){

		try {
			UserLoginResponseDto response = userService.login(requestDto);
			return new ApiResponse<>("SUCCESS", "로그인 성공", response);

		} catch (IllegalArgumentException e) {
			return new ApiResponse<>("FAIL", e.getMessage(), null );

		} catch (Exception e) {
			log.error("로그인 실패", e);
			return new ApiResponse<>("FAIL", "로그인 처리 중 오류가 발생했습니다.", null );
		}
    }


	@GetMapping("/{userId}")
	@Operation(summary = "사용자 조회")
	public ApiResponse<?> userDetail(@PathVariable String userId){
		try{
			UserResponseDto response = userService.getUserDto(userId);
			return new ApiResponse<>("SUCCESS", "조회 성공", response);

		}catch (IllegalArgumentException e) {
			return new ApiResponse<>("Fail", e.getMessage(), null);

		} catch (Exception e) {
			log.error("사용자 조회 중 예러 발생",e);
			return new ApiResponse<>("FAIL","조회 처리 중 오류가 발생했습니다.",null);
		}
	}

}
