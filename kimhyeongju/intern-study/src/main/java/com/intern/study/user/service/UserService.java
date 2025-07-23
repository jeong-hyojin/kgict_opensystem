package com.intern.study.user.service;

import com.intern.study.common.ApiResponse;
import com.intern.study.user.domain.UserEntity;
import com.intern.study.user.dto.UserLoginResponseDto;
import com.intern.study.user.dto.UserSignupRequestDto;
import com.intern.study.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private static final boolean ALERT_ENABLED = true;
    private static final boolean ALERT_DISABLED = false;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String signupUrl(
            String username,
            String email,
            String password
    ) {
        return "가입 확인 요청 " + username + "/" + email + "/" + password;
    }

    public Map<String, Object> signupFetch(
            String username,
            String email,
            String password
    ) {
        Map<String, Object> data = new HashMap<>();
        data.put("아이디", username);
        data.put("이메일", email);
        data.put("비밀번호", password);

        Map<String, Object> response = new HashMap<>();
        String code = username.equals("success") ? "SUCCESS" : "FAIL";
        response.put("code", code);
        response.put("data", data);
        response.put("alertEnabled", true);
        return response;
    }

    public Map<String, Object> signupPost(
            String username,
            String email
    ) {
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
        response.put("alertEnabled", true);
        return response;
    }

    public ApiResponse signupPostDto(
            String username
    ) {
        Map<String, Object> response = new HashMap<>();
        String code, message;
        if ("success".equals(username)) {
            code = "SUCCESS";
            message = "회원가입이 완료되었습니다.";
        } else {
            code = "FAIL";
            message = username + "는 이미 존재하는 아이디 입니다.";
        }
        response.put("username", username);
        response.put("alertEnabled", true);

        return ApiResponse.builder()
                .code(code)
                .message(message)
                .data(response)
                .build();
    }

    @Transactional
    public ApiResponse signupRequest(
            UserSignupRequestDto request
    ) {
        log.info(request.toString());
        try {
            request.encodePassword(passwordEncoder);

            UserEntity entity = request.toEntity();
            UserEntity savedEntity = userRepository.save(entity);

            return new ApiResponse<>("SUCCESS", ALERT_ENABLED, "회원가입에 성공하였습니다.", savedEntity.getId());
        } catch (Exception e) {
            log.error("회원가입 중 예외 발생", e);
            return new ApiResponse<>("FAIL", ALERT_ENABLED, "회원가입 중 오류가 발생했습니다.", null);
        }
    }

    @Transactional
    public ApiResponse login(
            String userId,
            String password
    ) {
        Optional<UserEntity> userOpt = userRepository.findByUserId(userId);

        if (userOpt.isEmpty()) {
            return new ApiResponse<>("FAIL", ALERT_ENABLED, "존재하지 않은 아이디 입니다.", null);
        }

        UserEntity entity = userOpt.get();

        if (!passwordEncoder.matches(password, entity.getPassword())) {
            return new ApiResponse<>("FAIL", ALERT_ENABLED, "비밀번호가 일치하지 않습니다.", null);
        }

        UserLoginResponseDto response = new UserLoginResponseDto(entity.getUserId(), entity.getUsername(), entity.getRole());
        return new ApiResponse<>("SUCCESS", ALERT_ENABLED, "로그인 성공", response);
    }

    @Transactional
    public ApiResponse getUserInfo(
            String userId
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
}
