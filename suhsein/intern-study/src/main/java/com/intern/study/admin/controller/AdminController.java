package com.intern.study.admin.controller;

import com.intern.study.admin.dto.AdminUserAddRequestDto;
import com.intern.study.common.ApiResponse;
import com.intern.study.user.domain.UserEntity;
import com.intern.study.user.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AdminController {
    UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 사용자 전체 조회
    @GetMapping("/user-list")
    public ApiResponse<?> userList() {
        return new ApiResponse<>("SUCCESS", "전체 사용자 목록", userRepository.findAll());
    }

    // 사용자 상세 정보
    @GetMapping("/{userId}")
    public ApiResponse<?> userDetail(@PathVariable String userId) {
        log.info(userId);
        try {
            Optional<UserEntity> userOpt = userRepository.findByUserId(userId);

            if(userOpt.isEmpty()) {
                return new ApiResponse<>("FAIL", "해당 사용자를 찾을 수 없습니다.", null);
            }
            UserEntity user = userOpt.get();

            return new ApiResponse<>("SUCCESS", "조회 성공", user);
        } catch(Exception e) {
            log.error("사용자 조회 중 예외 발생", e);
            return new ApiResponse<>("FAIL", "조회 처리 중 오류가 발생했습니다.", null);
        }
    }

    // 사용자 삭제
    // POST로 해도 동작은 함. Security Config로 깐깐하게 설계하는 경우 delete 메서드로만 요청 가능하게 할 수 있음
    // 가능하면 Restful API 설계 원칙에 맞춰서 method 설정하기
    @DeleteMapping("/user-delete/{userId}")
    public ApiResponse<?> userDelete(@PathVariable String userId) {
        try {
            // 1. 사용자 조회
            Optional<UserEntity> userOpt = userRepository.findByUserId(userId);

            if(userOpt.isEmpty()) {
                return new ApiResponse<>("FAIL", "존재하지 않는 사용자입니다.", null);
            }

            UserEntity user = userOpt.get();

            // 2. 관리자 계정 삭제 방지
            if("ADMIN".equals(user.getRole())){
                // 값이 있는 문자열을 앞에다가 준다. Null.~ 로 되는 경우 오류 발생
                return new ApiResponse<>("FAIL", "관리자 계정은 삭제할 수 없습니다.", null);
            }

            // 3. 사용자 삭제
            userRepository.delete(user);

            return new ApiResponse<>("SUCCESS", "사용자가 성공적으로 삭제되었습니다.", true);
        } catch (Exception e) {
            log.error("사용자 삭제 중 예외 발생", e);
            return new ApiResponse<>("FAIL", "사용자 삭제 중 오류가 발생하였습니다.", null);
        }
    }

    //사용자 추가
    @PostMapping("/user-add")
    public ApiResponse<?> userAdd(@RequestBody AdminUserAddRequestDto requestDto) {
        try{
            // 1. 임시 비밀번호를 userId+! 로 설정
            requestDto.encodeTempPassword(passwordEncoder);

            // 2. DTO -> Entity 변환
            UserEntity entity = requestDto.toEntity();

            // 3. Entity 저장 (JPA save) -> 저장 후 엔티티 반환
            UserEntity savedEntity = userRepository.save(entity);

            return new ApiResponse<>("SUCCESS", "임시사용자 등록에 성공하였습니다.", savedEntity.getId());
        } catch (Exception e) {
            log.error("임시사용자 등록 중 예외 발생", e); // - 예외 정보 로그로 출력
            return new ApiResponse<>("FAIL", "임시사용자 등록 중 오류가 발생하였습니다.", null);
        }
    }
}
