package com.intern.study.admin.controller;

import com.intern.study.common.ApiResponse;
import com.intern.study.user.domain.UserEntity;
import com.intern.study.user.dto.UserLoginResponseDto;
import com.intern.study.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private final UserRepository userRepository;

    @GetMapping("/user-list")
    public ApiResponse<?> userList() {
        List<UserEntity> all = userRepository.findAll();
        for (UserEntity user : all) {
            log.info(user.toString());
        }
        return new ApiResponse<>("SUCCESS","전체 사용자 목록",userRepository.findAll());
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
