package com.intern.study.user.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.intern.study.common.ApiResponse;
import com.intern.study.user.domain.UserEntity;
import com.intern.study.user.domain.UserLoginRequestDto;
import com.intern.study.user.domain.UserLoginResponseDto;
import com.intern.study.user.domain.UserSignupRequestDto;
import com.intern.study.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

    @GetMapping("/signup-url")
    public String signupUrl(@RequestParam String username,
                            @RequestParam String email,
                            @RequestParam String password) {

        return "가입 요청 확인 : username=" + username + ", email=" + email;
 
    }
    
    
    @GetMapping("/signup-fetch")
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
    
    @PostMapping("/signup-post-jpa")
    public ApiResponse<?> signupRequest(@RequestBody UserSignupRequestDto resquestDto){
    	
    	try {
    		
    		//비번 암호화
    		resquestDto.encodePassword(passwordEncoder);
    		
    		//Dto -> Entity
    		UserEntity entity = resquestDto.toEntity();
    		
    		//Entity Jpa 저장 -> 저장 후 entity 반환
    		UserEntity savedEntity = userRepository.save(entity);
    		
    		return new ApiResponse<>("SUCCESS", "회원가입 성공했습니다.", savedEntity.getId());
			
		} catch (Exception e) {
			log.error("회원가입 중 예외 발생",e);
			return new ApiResponse<>("Fail", "회원가입 중 오류가 발생했습니다.",null);
		}

    }    
    
    
    @PostMapping("/login")
    public ApiResponse<?> login(@RequestBody UserLoginRequestDto requestDto){
    	
    	String userId = requestDto.getUserId();
    	String password = requestDto.getPassword();
    	
    	//1. userId로 사용자 정보 조회
    	Optional<UserEntity> userOpt = userRepository.findByUserId(userId);
    	
    	if(userOpt.isEmpty()) {
    		return new ApiResponse<>("FAIL", "존재하지 않는 아이디입니다.", null);
    	}
    	
    	UserEntity user = userOpt.get();
    	
    	if(!passwordEncoder.matches(password, user.getPassword())){
    		return new ApiResponse<>("FAIL", "비밀번호가 일치하지 않습니다.", null);
    	}
    	//응답용 사용자 정보 변환후 반환
    	UserLoginResponseDto dto = new UserLoginResponseDto(user);
    	
    	return new ApiResponse<>("SUCCESS", "로그인 성공", dto);
    }


	@GetMapping("/{userId}")
	public ApiResponse<?> userDetail(@PathVariable String userId){

		log.info(userId);
		try{
			Optional<UserEntity> userOpt = userRepository.findByUserId(userId);

			if(userOpt.isEmpty()) {
				return new ApiResponse<>("Fail","해당 사용자를 찾을 수 없습니다.", null);
			}
			UserEntity user = userOpt.get();

			return new ApiResponse<>("SUCCESS", "조회 성공",user);

		}catch (Exception e) {
			log.error("사용자 조회 중 예러 발생",e);

			return new ApiResponse<>("FAIL","조회 처리 중 오류가 발생했습니다.",null);
		}

	}




}
