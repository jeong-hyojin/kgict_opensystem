package com.intern.study.user.controller;

import com.intern.study.common.ApiResponse;
import com.intern.study.user.domain.UserEntity;
import com.intern.study.user.dto.UserLoginRequestDto;
import com.intern.study.user.dto.UserLoginResponseDto;
import com.intern.study.user.dto.UserSignupRequestDto;
import com.intern.study.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/signup-url")
    public String signupUrl(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password
    ) {
        return "가입 확인 요청 " + username + "/" + email + "/" + password;
    }

    @GetMapping("/signup-fetch")
    public Map<String, Object> signupFetch(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password
    ) {
        Map<String, Object> data = new HashMap<>();
        data.put("아이디", username);
        data.put("이메일", email);
        data.put("비밀번호", password);

        Map<String, Object> response = new HashMap<>();
        String code = username.equals("success") ? "SUCCESS" : "FAIL";
        response.put("code", code);
        response.put("data", data);

        return response;
    }

    @PostMapping("/signup-post-map")
    public Map<String, Object> signupPost(
            @RequestBody Map<String, String> params
    ) {
        String username = params.get("username");
        String email = params.get("email");

        Map<String, Object> data = new HashMap<>();
        data.put("아이디", username);
        data.put("이메일", email);

        Map<String, Object> response = new HashMap<>();
        if ("success".equals(username)) {
            response.put("code", "SUCCESS");
            response.put("message", "회원가입이 완료되었습니다.");
        } else {
            response.put("code", "FAIL");
            response.put("message", username + "는 이미 존재하는 아이디 입니다.");
        }
        response.put("data", data);
        return response;
    }

    @PostMapping("/signup-post-dto")
    public ApiResponse<?> signupPostDto(
            @RequestBody UserSignupRequestDto request
    ) {

        Map<String, Object> response = new HashMap<>();
        String username = request.getUsername();
        String code, message;

        if ("success".equals(username)) {
            code = "SUCCESS";
            message = "회원가입이 완료되었습니다.";
        } else {
            code = "FAIL";
            message = username + "는 이미 존재하는 아이디 입니다.";
        }
        response.put("uername", username);

        return ApiResponse.builder()
                .code(code)
                .message(message)
                .data(response)
                .build();
    }

    @PostMapping("/signup-post-jpa")
    public ApiResponse<?> signupRequest(
            @RequestBody UserSignupRequestDto request
    ) {
        log.info(request.toString());
        try {
            request.encodePassword(passwordEncoder);

            UserEntity entity = request.toEntity();
            UserEntity savedEntity = userRepository.save(entity);

            return new ApiResponse<>("SUCCESS", "회원가입에 성공하였습니다.", savedEntity.getId());
        } catch (Exception e) {
            log.error("회원가입 중 예외 발생", e);
            return new ApiResponse<>("FAIL", "회원가입 중 오류가 발생했습니다.", null);

        }
    }

    @PostMapping("login")
    public ApiResponse<?> login(
            @RequestBody UserLoginRequestDto request
    ) {
        String userId = request.getUserId();
        String password = request.getPassword();

        Optional<UserEntity> userOpt = userRepository.findByUserId(userId);

        if (userOpt.isEmpty()) {
            return new ApiResponse<>("FAIL", "존재하지 않은 아이디 입니다.", null);
        }

        UserEntity entity = userOpt.get();

        if (!passwordEncoder.matches(password, entity.getPassword())) {
            return new ApiResponse<>("FAIL", "비밀번호가 일치하지 않습니다.", null);
        }

        UserLoginResponseDto response = new UserLoginResponseDto(entity.getUserId(), entity.getUsername(), entity.getRole());
        return new ApiResponse<>("SUCCESS", "로그인 성공", response);
    }

    @GetMapping("/{userId}")
    public ApiResponse<?> getUserInfo(
            @PathVariable String userId
    ) {
        log.info(userId);
        try {
            Optional<UserEntity> userOpt = userRepository.findByUserId(userId);

            if (userOpt.isEmpty()) {
                return new ApiResponse<>("FAIL", "해당 사용자를 찾을 수 없습니다.", null);
            }

            UserEntity entity = userOpt.get();
			UserLoginResponseDto response = new UserLoginResponseDto(entity.getUserId(), entity.getUsername(), entity.getRole());
			log.info(response.toString());
			return new ApiResponse<>("SUCCESS", "조회 성공", response);
        } catch (Exception e) {
            log.error("사용자 조회 중 예외 발생", e);
            return new ApiResponse<>("FAIL", "조회 처리 중 오류가 발생했습니다.", null);
        }
    }
}
