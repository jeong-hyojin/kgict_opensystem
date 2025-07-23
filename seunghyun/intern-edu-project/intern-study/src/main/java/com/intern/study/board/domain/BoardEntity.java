package com.intern.study.board.domain;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                                            //게시글 번호
    private Long UUID;                                          //UUID
    private String title;                                       //제목
    private String content;                                     //내용
    private String password;                                    //비밀번호

    @CreationTimestamp
    private LocalDateTime createdDate = LocalDateTime.now();   //작성일

    @UpdateTimestamp
    private LocalDateTime updatedDate = LocalDateTime.now();   //수정일

}
