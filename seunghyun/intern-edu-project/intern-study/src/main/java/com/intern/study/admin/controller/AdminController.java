package com.intern.study.admin.controller;

import com.intern.study.admin.domain.AdminUpdateRequestDto;
import com.intern.study.admin.domain.AdminUserAddRequestDto;
import com.intern.study.common.ApiResponse;
import com.intern.study.user.domain.UserEntity;
import com.intern.study.user.domain.UserResponseDto;
import com.intern.study.user.repository.UserRepository;
import com.intern.study.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    //사용자 전체 조회
    // userRepository.findAll() 도 dto로 변결할 예정(Service 단)
    @GetMapping("/user-list")
    public ApiResponse<?> userList(){

        log.info("사용자 전체 조회 userList()");
        return new ApiResponse<>("SUCCESS", "전체 사용자 목록", userRepository.findAll());
    }

    //사용자 상세 정보
    @GetMapping("/{userId}")
    public ApiResponse<?> userDetail(@PathVariable String userId){

        log.info(userId);
        try{
            Optional<UserEntity> userOpt = userRepository.findByUserId(userId);

            if(userOpt.isEmpty()) {
                return new ApiResponse<>("FAIL","해당 사용자를 찾을 수 없습니다.", null);
            }
            UserEntity user = userOpt.get();
            //log.info("UserEntity : "+  user.toString());
            UserResponseDto responseDto = new UserResponseDto(user);

            return new ApiResponse<>("SUCCESS", "조회 성공",responseDto);

        }catch (Exception e) {
            log.error("사용자 조회 중 에러 발생",e);

            return new ApiResponse<>("FAIL","조회 처리 중 오류가 발생했습니다.",null);
        }
    }

    //사용자 삭제
    @DeleteMapping("/user-delete/{userId}")
    public ApiResponse<?> userDelete(@PathVariable String userId){

        try{
            Optional<UserEntity> userOpt = userRepository.findByUserId(userId);

            if( userOpt.isEmpty()){
                return new ApiResponse<>("FAIL","존재하지 않는 사용자입니다.",null);
            }

            UserEntity user = userOpt.get();

            //관리자용 삭제 방지
            //user.getRole().equals("ADMIN") 은 NullPointException 발생 가능
            if("ADMIN".equals(user.getRole())){
                log.info("관리자용 삭제 방지!!");
                return new ApiResponse<>("FAIL", "관리자 계정은 삭제할 수 없습니다.", null);
            }

            userRepository.delete(user);

            return new ApiResponse<>("SUCCESS", "사용자가 성공적으로 삭제됐습니다.",null);

        } catch (Exception e) {
            log.error("사용자 삭제 중 예외 발생", e);
            return new ApiResponse<>("FAIL", "사용자 삭제 중 오류가 발생했습니다.", null);
        }
    }

    //사용자 추가
    @PostMapping("user-add")
    public ApiResponse<?> signupRequest(@RequestBody AdminUserAddRequestDto requestDto){

        try{
            requestDto.encodeTempPassword(passwordEncoder);

            UserEntity userEntity = requestDto.toEntity();
            UserEntity savedEntity = userRepository.save(userEntity);

            return new ApiResponse<>("SUCCESS", "임시 사용자 등록에 성공하셨습니다.", savedEntity.getId());

        }catch (Exception e ){
            log.error("임시 사용자 등록 중 예외 발생",e);
            return new ApiResponse<>("FAIL", "임시 사용자 등록 중 오류가 발생했습니다.", null);
        }
    }

    //사용자 수정
    @PutMapping("/update")
    public ApiResponse<?> updateUser(@RequestBody AdminUpdateRequestDto requestDto){

        try{
            UserEntity user = userService.getUser(requestDto.getUserId());

            user.setUserId(requestDto.getUserId());
            user.setPassword(requestDto.getPassword());
            user.setEmail(requestDto.getEmail());
            user.setRole(requestDto.getRole());

            userRepository.save(user);

            return new ApiResponse<>("SUCCESS", "사용자 수정에 성공하셨습니다.",user.getUserId());

        }catch (Exception e ){
            log.error("사용자 수정 중 예외 발생",e);
            return new ApiResponse<>("FAIL", "사용자 수정 중 오류가 발생했습니다.", null);
        }
    }

}