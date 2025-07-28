package com.intern.study.admin.controller;

import com.intern.study.admin.service.AdminService;
import com.intern.study.common.domain.AdminUserAddRequestDto;
import com.intern.study.common.domain.ApiResponse;
import com.intern.study.user.domain.UserEntity;
import com.intern.study.user.repository.UserRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@Tag(name = "관리자", description = "관리자 기능 API")
public class AdminController {

    private final AdminService adminService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/user-list")
    @Operation(summary = "전체 사용자 목록 조회", description = "시스템에 등록된 모든 사용자 목록을 반환합니다.")
    public ApiResponse<?> userList() {
        return adminService.getUserList();
    }

    @GetMapping("/{userId}")
    @Operation(summary = "사용자 상세 조회", description = "지정된 userId를 가진 사용자의 상세 정보를 반환합니다.")
    public ApiResponse<?> userDetail(@PathVariable String userId) {
        return adminService.getUserDetail(userId);
    }

    @DeleteMapping("/user-delete/{userId}")
    @Operation(summary = "사용자 삭제", description = "userId에 해당하는 사용자를 삭제합니다. ")
    public ApiResponse<?> userDelete(@PathVariable String userId) {
        try {
            Optional<UserEntity> userOpt = userRepository.findByUserId(userId);

            if (userOpt.isEmpty()) {
                return new ApiResponse<>("FAIL", "존재하지 않는 사용자입니다.", null);
            }

            UserEntity user = userOpt.get();

            if ("ADMIN".equals(user.getRole())) {
                return new ApiResponse<>("FAIL", "관리자 계정은 삭제할 수 없습니다.", null);
            }

            userRepository.delete(user);

            return new ApiResponse<>("SUCCESS", "사용자가 성공적으로 삭제되었습니다.", true);

        } catch (Exception e) {
            log.error("사용자 삭제 중 예외 발생", e);
            return new ApiResponse<>("FAIL", "사용자 삭제 중 오류가 발생했습니다.", null);
        }
    }

    @PostMapping("/user-add")
    @Operation(summary = "임시 사용자 등록", description = "TEMP 권한을 가진 임시 사용자를 등록합니다. ")
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
