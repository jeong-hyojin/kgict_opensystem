package com.intern.study.user.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String 		    userId;     // 아이디
    private String 		    password;   // 비밀번호
    private String 		    username;   // 사용자 이름
    private String 		    email;      // 이메일
    private String 		    phone;      // 전화번호
    private String 		    role;       // 직급
    private Boolean         isActive;   // 사용 여부
    private LocalDateTime   regDate; 	// 가입일
}
