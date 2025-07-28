package com.intern.study.admin.domain;

import com.intern.study.user.domain.UserEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Data
@Schema(name = "관리자 사용자 수정 Request")
public class AdminUpdateRequestDto {

    @Schema(title = "사용자 ID", maxLength = 255, requiredMode = Schema.RequiredMode.REQUIRED)
    private String userId;
    
    @Schema(title = "비밀번호", maxLength = 255, description = "입력하지 않으면 기본값 '1111'로 설정")
    private String password;
    
    @Schema(title = "사용자명", maxLength = 255, requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;
    
    @Schema(title = "이메일", maxLength = 255, requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;
    
    @Schema(title = "전화번호", maxLength = 20)
    private String phone;
    
    @Schema(title = "권한", allowableValues = {"USER", "ADMIN", "TEMP"}, requiredMode = Schema.RequiredMode.REQUIRED)
    private String role;
    
    @Schema(title = "활성화 여부", requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isActive;

    public void encodeTempPassword(PasswordEncoder passwordEncoder){
        //user_list.html의 사용자 추가 시, 비밀번호 입력이 없어서 임의로 1111 암호화
        this.password = passwordEncoder.encode("1111");
    }

    public UserEntity toEntity(){
        return UserEntity.builder()
                .userId(this.userId)
                .password(this.password)
                .username(this.username)
                .email(this.email)
                .phone(this.phone)
                .role(this.role)
                .isActive(isActive)
                .regDate(LocalDateTime.now())
                .build();
    }

}
