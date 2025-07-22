package com.intern.study.board.domain;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardRequestDto {
    private Long UUID;
    private String title;
    private String content;
    private String password;

    public BoardEntity toEntity(){

        return BoardEntity.builder()
                          .UUID(this.UUID)
                          .title(this.title)
                          .content(this.content)
                          .password(this.password)
                          .createdDate(LocalDateTime.now())
                          .updatedDate(LocalDateTime.now())
                          .build();
    }
}
