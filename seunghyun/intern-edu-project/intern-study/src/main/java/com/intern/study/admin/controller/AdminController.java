package com.intern.study.admin.controller;

import com.intern.study.common.ApiResponse;
import com.intern.study.user.domain.UserEntity;
import com.intern.study.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final UserRepository userRepository;

    //사용자 전체 조회
        // userRepository.findAll() 도 dto로 변결할 예정(Service 단)
    @GetMapping("/user-list")
    public ApiResponse<?> userList(){

        System.out.println(userRepository.findAll());
        return new ApiResponse<>("SUCCESS", "전체 사용자 목록", userRepository.findAll());
    }

    //사용자 상세 정보
    @GetMapping("/{userId}")
    public ApiResponse<?> userDetail(@PathVariable String userId){

        log.info(userId);
        try{
            Optional<UserEntity> userOpt = userRepository.findByUserId(userId);

            if(userOpt.isEmpty()) {
                return new ApiResponse<>("Fail","해당 사용자를 찾을 수 없습니다.", null);
            }
            UserEntity user = userOpt.get();

            return new ApiResponse<>("SUCCESS", "조회 성공",user);

        }catch (Exception e) {
            log.error("사용자 조회 중 에러 발생",e);

            return new ApiResponse<>("FAIL","조회 처리 중 오류가 발생했습니다.",null);
        }

    }


}
