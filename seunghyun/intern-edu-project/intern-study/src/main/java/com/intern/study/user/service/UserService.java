package com.intern.study.user.service;

import com.intern.study.user.domain.*;
import com.intern.study.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Slf4j
@Setter
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 공통 getUser 메서드
    public UserEntity getUser(String userId) {

        UserEntity userEntity = userRepository.findByUserId(userId)
                .orElseThrow( () -> new IllegalArgumentException("존재하지 않는 아이디입니다."));

        return userEntity;
    }

    //UserEntity -> DTO 전환
    public UserResponseDto getUserDto(String userId) {
        UserEntity user = getUser(userId);
        return new UserResponseDto(user);
    }

    //회원가입
    @Transactional
    public Long signupRequest(UserSignupRequestDto requestDto) {

        //비밀번호 암호화
        requestDto.encodePassword(passwordEncoder);

        //DTO -> Entity 변환
        UserEntity entity = requestDto.toEntity();

        //Entity 저장(JPA save)
        UserEntity saveEntity = userRepository.save(entity);

        return saveEntity.getId();
    }

    //로그인
    @Transactional
    public UserLoginResponseDto login(UserLoginRequestDto requestDto) {

        String userId = requestDto.getUserId();
        String password = requestDto.getPassword();

        //1. getUser() 함수 호출 해 userId로 사용자 조회
        UserEntity user = getUser(userId);

        //2. 비밀번호 확인
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        //3. 응답용 사용자 정보 변환후 반환
        UserLoginResponseDto dto = new UserLoginResponseDto(user);

        return dto;
    }

}