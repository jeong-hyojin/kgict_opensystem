package com.intern.study.board.domain;


import lombok.Data;

@Data
public class BoardRequestDto {

    private String uuid;            // 수정 시 필요
    private String title;
    private String content;
    private String password;

    public BoardEntity toEntity(){

        return BoardEntity.builder()
                .title(this.title)
                .content(this.content)
                .password(this.password)
                .build();
    }
}