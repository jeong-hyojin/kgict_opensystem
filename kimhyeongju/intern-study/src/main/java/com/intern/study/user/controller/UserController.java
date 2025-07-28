package com.intern.study.user.controller;

import com.intern.study.common.ApiResponse;
import com.intern.study.user.dto.ChangePasswordRequestDto;
import com.intern.study.user.dto.UserLoginRequestDto;
import com.intern.study.user.dto.UserSignupRequestDto;
import com.intern.study.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@Tag(name = "사용자")
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/signup-url")
    @Operation(summary = "가입 요청 확인 TEST")
    public String signupUrl(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password
    ) {
        return userService.signupUrl(username, email, password);
    }

    @GetMapping("/signup-fetch")
    @Operation(summary = "가입 요청 FETCH TEST")
    public Map<String, Object> signupFetch(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password
    ) {
        return userService.signupFetch(username, email, password);
    }

    @PostMapping("/signup-post-map")
    @Operation(summary = "가입 요청 FETCH-POST TEST")
    public Map<String, Object> signupPost(
            @RequestBody Map<String, String> params
    ) {
        String username = params.get("username");
        String email = params.get("email");
        return userService.signupPost(username, email);
    }

    @PostMapping("/signup-post-dto")
    @Operation(summary = "가입 요청 FETCH-DTO TEST")
    public ApiResponse<?> signupPostDto(
            @RequestBody UserSignupRequestDto request
    ) {
        String username = request.getUsername();
        return userService.signupPostDto(username);
    }

    @PostMapping("/signup-post-jpa")
    @Operation(summary = "가입 요청 FETCH-JPA TEST")
    public ApiResponse<?> signupRequest(
            @RequestBody UserSignupRequestDto request
    ) {
        return userService.signupRequest(request);
    }

    @PostMapping("login")
    @Operation(summary = "로그인 요청 API")
    public ApiResponse<?> login(
            @RequestBody UserLoginRequestDto request
    ) {
        String userId = request.getUserId();
        String password = request.getPassword();
        return userService.login(userId, password);
    }

    @GetMapping("/{userId}")
    @Operation(summary = "사용자 정보 요청 API")
    public ApiResponse<?> getUserInfo(
            @PathVariable String userId
    ) {
        return userService.getUserInfo(userId);
    }

    @PostMapping("/change-password")
    @Operation(summary = "비밀번호 변경 API")
    public ApiResponse<?> changePassword(
            @RequestBody ChangePasswordRequestDto request
    ) {
        log.info("임시 사용자 비밀번호 변경 요청 : " + request.toString());
        return userService.changePassword(request);
    }
}
