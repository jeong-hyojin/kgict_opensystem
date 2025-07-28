package com.intern.study.common.domain;

import com.intern.study.user.domain.UserEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Getter
@Setter
@Schema(name = "관리자 사용자 추가 Request")
public class AdminUserAddRequestDto {

    @Schema(title = "사용자ID", maxLength = 255, requiredMode = Schema.RequiredMode.REQUIRED)
    private String userId;

    @Schema(title = "비밀번호", maxLength = 255, description = "자동으로 userId + '!' 형식으로 설정됨", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String password;

    @Schema(title = "사용자 이름", maxLength = 255, requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;

    @Schema(title = "이메일", maxLength = 255, requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    public void encodeTempPassword(PasswordEncoder encoder) {
        this.password = encoder.encode(this.userId + "!");
    }

    public UserEntity toEntity() {
        return UserEntity.builder()
                .userId(userId)
                .password(password)
                .username(username)
                .email(email)
                .role("TEMP")             // 임시 사용자 권한
                .isActive(false)          // 비활성 상태
                .regDate(LocalDateTime.now())
                .build();
    }
}
