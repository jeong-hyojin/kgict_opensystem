package com.intern.study.user.controller;

import com.intern.study.common.ApiResponse;
import com.intern.study.user.dto.UserLoginRequestDto;
import com.intern.study.user.dto.UserSignupRequestDto;
import com.intern.study.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/signup-url")
    public String signupUrl(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password
    ) {
        return userService.signupUrl(username, email, password);
    }

    @GetMapping("/signup-fetch")
    public Map<String, Object> signupFetch(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password
    ) {
        return userService.signupFetch(username, email, password);
    }

    @PostMapping("/signup-post-map")
    public Map<String, Object> signupPost(
            @RequestBody Map<String, String> params
    ) {
        String username = params.get("username");
        String email = params.get("email");
        return userService.signupPost(username, email);
    }

    @PostMapping("/signup-post-dto")
    public ApiResponse<?> signupPostDto(
            @RequestBody UserSignupRequestDto request
    ) {
        String username = request.getUsername();
        return userService.signupPostDto(username);
    }

    @PostMapping("/signup-post-jpa")
    public ApiResponse<?> signupRequest(
            @RequestBody UserSignupRequestDto request
    ) {
        return userService.signupRequest(request);
    }

    @PostMapping("login")
    public ApiResponse<?> login(
            @RequestBody UserLoginRequestDto request
    ) {
        String userId = request.getUserId();
        String password = request.getPassword();
        return userService.login(userId, password);
    }

    @GetMapping("/{userId}")
    public ApiResponse<?> getUserInfo(
            @PathVariable String userId
    ) {
        return userService.getUserInfo(userId);
    }
}
