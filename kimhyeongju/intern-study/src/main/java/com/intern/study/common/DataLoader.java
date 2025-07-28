package com.intern.study.common;

import com.intern.study.user.domain.UserEntity;
import com.intern.study.user.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DataLoader {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init(){
        userRepository.save(
                UserEntity.builder()
                        .userId("qwer")
                        .password(passwordEncoder.encode("qwer1234"))
                        .username("김형주")
                        .email("test@example.com")
                        .phone("010-3664-3521")
                        .role("USER")
                        .isActive(true)
                        .regDate(LocalDateTime.now())
                        .build()
        );

        userRepository.save(
                UserEntity.builder()
                        .userId("temp01")
                        .password(passwordEncoder.encode("temp01!"))
                        .username("tempUser1")
                        .email("temp@example.com")
                        .phone("010-3664-3521")
                        .role("TEMP")
                        .isActive(true)
                        .regDate(LocalDateTime.now())
                        .build()
        );
    }
}
