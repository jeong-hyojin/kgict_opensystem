package com.intern.study.board.domain;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                    //게시글 번호
    private Long UUID;                  //UUID
    private String title;               //제목
    private String content;             //내용
    private String password;            //비밀번호
    private LocalDateTime createdDate;  //작성일
}
