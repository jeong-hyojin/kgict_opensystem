package com.intern.study.board.domain;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                   //DB의 PK
    private String uuid;               //비즈니스 로직의 식별자 uuid
    private String title;              //제목
    private String content;            //내용
    private String password;           //비밀번호

    /* 작성시간, 수정 시간 자동 생성*/
    @CreationTimestamp
    private LocalDateTime createdAt;   //작성 시간
    @UpdateTimestamp
    private LocalDateTime updatedAt;   //수정 시간

    /* Entity가 DB에 처음 저장되기 직전에 자동 생성 */
    @PrePersist
    public void generateUUID() {
        this.uuid = UUID.randomUUID().toString();
    }

}
