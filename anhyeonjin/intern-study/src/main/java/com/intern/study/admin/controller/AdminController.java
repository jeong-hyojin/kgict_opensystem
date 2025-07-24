package com.intern.study.admin.controller;

import com.intern.study.admin.service.AdminService;
import com.intern.study.common.domain.AdminUserAddRequestDto;
import com.intern.study.common.domain.ApiResponse;
import com.intern.study.user.domain.UserEntity;
import com.intern.study.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 전체 사용자 목록을 조회
    @GetMapping("/user-list")
    public ApiResponse<?> userList() {
        return adminService.getUserList();
    }

    // 특정 사용자 정보를 조회
    @GetMapping("/{userId}")
    public ApiResponse<?> userDetail(@PathVariable String userId) {
        return adminService.getUserDetail(userId);
    }

    // 사용자 삭제
    @DeleteMapping("/user-delete/{userId}")
    public ApiResponse<?> userDelete(@PathVariable String userId) {
        try {
            Optional<UserEntity> userOpt = userRepository.findByUserId(userId);

            if (userOpt.isEmpty()) {
                return new ApiResponse<>("FAIL", "존재하지 않는 사용자입니다.", null);
            }

            UserEntity user = userOpt.get();

            if ("ADMIN".equals(user.getRole())) { // null 방지용으로 "ADMIN"이 앞
                return new ApiResponse<>("FAIL", "관리자 계정은 삭제할 수 없습니다.", null);
            }

            userRepository.delete(user);

            return new ApiResponse<>("SUCCESS", "사용자가 성공적으로 삭제되었습니다.", true);

        } catch (Exception e) {
            log.error("사용자 삭제 중 예외 발생", e);
            return new ApiResponse<>("FAIL", "사용자 삭제 중 오류가 발생했습니다.", null);
        }
    }

    @PostMapping("user-add")
    public ApiResponse<?> signupRequest(@RequestBody AdminUserAddRequestDto requestDto) {
        try {
            requestDto.encodeTempPassword(passwordEncoder);

            UserEntity entity = requestDto.toEntity();
            UserEntity savedEntity = userRepository.save(entity);

            return new ApiResponse<>("SUCCESS", "임시사용자 등록에 성공하였습니다.", savedEntity.getId());
        } catch (Exception e) {
            log.error("임시 사용자 등록 중 예외 발생", e);
            return new ApiResponse<>("FAIL", "임시 사용자 등록 중 오류가 발생했습니다.", null);
        }
    }



}
