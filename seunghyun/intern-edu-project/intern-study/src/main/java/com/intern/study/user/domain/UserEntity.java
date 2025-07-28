package com.intern.study.user.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String 		    userId;                             // 아이디
    private String 		    password;                           // 비밀번호
    private String 		    username;                           // 사용자 이름
    private String 		    email;                              // 이메일
    private String 		    phone;                              // 전화번호
    private String 		    role;                               // 권한
    private Boolean         isActive = false;                   // 사용 여부
    private LocalDateTime   regDate  = LocalDateTime.now(); 	// 가입일
}
