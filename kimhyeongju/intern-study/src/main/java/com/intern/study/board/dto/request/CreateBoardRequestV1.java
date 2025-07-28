package com.intern.study.board.dto.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CreateBoardRequestV1 {
    private String userId;
    private String title;
    private String content;
    private String password;

    public CreateBoardRequestV1(
            String userId,
            String title,
            String content,
            String password) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.password = password;
    }
}