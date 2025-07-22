package com.intern.study.board.dto.request;

import lombok.Data;

@Data
public class BoardCreateRequest {
    private String title;
    private String content;
    private String password;
}