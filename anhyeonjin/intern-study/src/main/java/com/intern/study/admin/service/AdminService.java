package com.intern.study.admin.service;

import com.intern.study.common.domain.ApiResponse;
import com.intern.study.user.domain.UserEntity;
import com.intern.study.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;

    public ApiResponse<?> getUserList() {
        return new ApiResponse<>("SUCCESS", "전체 사용자 목록", userRepository.findAll());
    }

    public ApiResponse<?> getUserDetail(String userId) {
        log.info(userId);
        try {
            Optional<UserEntity> userOpt = userRepository.findByUserId(userId);

            if (userOpt.isEmpty()) {
                return new ApiResponse<>("FAIL", "해당 사용자를 찾을 수 없습니다.", null);
            }
            UserEntity user = userOpt.get();
            return new ApiResponse<>("SUCCESS", "조회 성공", user);

        } catch (Exception e) {
            log.error("사용자 조회 중 예외 발생", e);
            return new ApiResponse<>("FAIL", "조회 처리 중 오류가 발생했습니다.", null);
        }
    }
}
