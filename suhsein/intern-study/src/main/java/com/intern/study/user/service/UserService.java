package com.intern.study.user.service;

import com.intern.study.common.ApiResponse;
import com.intern.study.user.domain.UserEntity;
import com.intern.study.user.dto.UserLoginResponseDto;
import com.intern.study.user.dto.UserSignupRequestDto;
import com.intern.study.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserService {
    UserRepository  userRepository;
    PasswordEncoder passwordEncoder;

    @Transactional
    public String signupRequest(UserSignupRequestDto requestDto) {
        requestDto.encodePassword(passwordEncoder);
        UserEntity entity      = UserEntity.from(requestDto);
        UserEntity savedEntity = userRepository.save(entity);
        return savedEntity.getUserId();
    }

    public UserLoginResponseDto login(String userId, String password) {
        UserEntity user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디입니다."));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return UserLoginResponseDto.from(user);
    }

    public UserEntity userDetail(String userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));
    }
}
