package com.intern.study.admin.controller;

import com.intern.study.admin.dto.AdminUserAddRequestDto;
import com.intern.study.common.ApiResponse;
import com.intern.study.user.domain.UserEntity;
import com.intern.study.user.dto.UserLoginResponseDto;
import com.intern.study.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private static final boolean ALERT_ENABLED = true;
    private static final boolean ALERT_DISABLED = false;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/user-list")
    public ApiResponse<?> userList() {
        List<UserEntity> all = userRepository.findAll();
        for (UserEntity user : all) {
            log.info(user.toString());
        }
        return new ApiResponse<>("SUCCESS", ALERT_ENABLED, "전체 사용자 목록", userRepository.findAll());
    }

    @GetMapping("/{userId}")
    public ApiResponse<?> getUserInfo(
            @PathVariable String userId
    ) {
        log.info(userId);
        try {
            Optional<UserEntity> userOpt = userRepository.findByUserId(userId);

            if (userOpt.isEmpty()) {
                return new ApiResponse<>("FAIL", ALERT_ENABLED, "해당 사용자를 찾을 수 없습니다.", null);
            }

            UserEntity entity = userOpt.get();
            UserLoginResponseDto response = new UserLoginResponseDto(entity.getUserId(), entity.getUsername(), entity.getRole());
            log.info(response.toString());
            return new ApiResponse<>("SUCCESS", ALERT_DISABLED, "조회 성공", response);
        } catch (Exception e) {
            log.error("사용자 조회 중 예외 발생", e);
            return new ApiResponse<>("FAIL", ALERT_ENABLED, "조회 처리 중 오류가 발생했습니다.", null);
        }
    }

    @DeleteMapping("/user-delete/{userId}")
    public ApiResponse<?> userDelete(
            @PathVariable String userId
    ) {
        try {
            Optional<UserEntity> userOpt = userRepository.findByUserId(userId);

            if (userOpt.isEmpty()) {
                return new ApiResponse<>("FAIL", true, "존재하지 않은 사용자입니다.", null);
            }

            UserEntity user = userOpt.get();

            // NullPointException 방지
            if ("ADMIN".equals(user.getRole())) {
                return new ApiResponse<>("FAIL", true, "관리자 계정은 삭제할 수 없습니다.", null);
            }
            userRepository.delete(user);

            return new ApiResponse<>("SUCCESS", true, "사용자가 성공적으로 삭제되었습니다.", null);
        } catch (Exception e) {
            log.error("사용자 삭제 중 예외 발생", e);
            return new ApiResponse<>("FAIL", true, "사용자 삭제 중 오류가 발생했습니다.", null);
        }
    }

    @PostMapping("user-add")
    public ApiResponse<?> signupRequest(
            @RequestBody AdminUserAddRequestDto request
    ) {
        try {
            request.encodeTempPassword(passwordEncoder);

            UserEntity entity = request.toEntity();

            UserEntity saveEntity = userRepository.save(entity);

            return new ApiResponse<>("SUCCESS", true, "임시사용자 등록에 성공하셨습니다.", saveEntity.getId());
        } catch (Exception e) {
            log.error("임시 사용자 등록 중 예외 발생", e);
            return new ApiResponse<>("FAIL", true, "임시 사용자 등록 중 오류가 발생했습니다.", null);
        }
    }
}
