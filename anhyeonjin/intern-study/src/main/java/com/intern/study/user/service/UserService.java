package com.intern.study.user.service;

import com.intern.study.common.domain.ApiResponse;
import com.intern.study.common.domain.UserSignupRequestDto;
import com.intern.study.user.domain.UserEntity;
import com.intern.study.user.domain.UserLoginRequestDto;
import com.intern.study.user.domain.UserLoginResponseDto;
import com.intern.study.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입 (DB에 사용자 등록)
    @Transactional
    public ApiResponse<?> signupRequest(UserSignupRequestDto requestDto) {
        try {
            requestDto.encodePassword(passwordEncoder);
            UserEntity entity = requestDto.toEntity();
            UserEntity savedEntity = userRepository.save(entity);
            return new ApiResponse<>("SUCCESS", "회원가입에 성공했습니다.", savedEntity.getId());
        } catch (Exception e) {
            log.error("회원가입 중 예외 발생", e);
            return new ApiResponse<>("FAIL", "회원가입 실패", null);
        }
    }

    // 테스트용 회원가입 처리
    @Transactional
    public Map<String, Object> signupPost(Map<String, String> params) {
        String username = params.get("username");
        String email = params.get("email");

        Map<String, Object> data = new HashMap<>();
        data.put("아이디", username);
        data.put("이메일", email);

        Map<String, Object> response = new HashMap<>();

        if ("success".equals(username)) {
            response.put("code", "SUCCESS");
            response.put("message", "회원가입이 완료됐습니다.");
        } else {
            response.put("code", "FAIL");
            response.put("message", username + "은 이미 등록된 아이디입니다.");
        }

        response.put("data", data);
        return response;
    }

    // 테스트용 회원가입 조건 확인
    public ApiResponse<?> signupTestWithCondition(UserSignupRequestDto requestDto) {
        Map<String, Object> response = new HashMap<>();
        String username = requestDto.getUsername();
        response.put("username", username);

        if ("success".equals(username)) {
            return new ApiResponse<>("SUCCESS", "회원가입이 완료됐습니다.", response);
        } else {
            return new ApiResponse<>("FAIL", username + "이미 등록된 아이디입니다.", response);
        }
    }

    // 로그인 처리
    public ApiResponse<?> login(UserLoginRequestDto requestDto) {
        String userId = requestDto.getUserId();
        String password = requestDto.getPassword();

        Optional<UserEntity> userOpt = userRepository.findByUserId(userId);

        if (userOpt.isEmpty()) {
            return new ApiResponse<>("FAIL", "존재하지 않는 아이디입니다.", null);
        }

        UserEntity user = userOpt.get();

        if (!passwordEncoder.matches(password, user.getPassword())) {
            return new ApiResponse<>("FAIL", "비밀번호가 일치하지 않습니다.", null);
        }

        return new ApiResponse<>("SUCCESS", "로그인 성공", new UserLoginResponseDto(user));
    }

    // 사용자 상세 조회
    public ApiResponse<?> userDetail(String userId) {
        try {
            Optional<UserEntity> userOpt = userRepository.findByUserId(userId);

            if (userOpt.isEmpty()) {
                return new ApiResponse<>("FAIL", "해당 사용자를 찾을 수 없습니다.", null);
            }

            return new ApiResponse<>("SUCCESS", "조회 성공", userOpt.get());
        } catch (Exception e) {
            log.error("사용자 조회 중 예외 발생", e);
            return new ApiResponse<>("FAIL", "조회 처리 중 오류가 발생했습니다.", null);
        }
    }
}
