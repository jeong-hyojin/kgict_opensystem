package com.intern.study.board.dto;

import lombok.Data;

@Data
public class BoardCreateRequest {
    private String title;
    private String content;
    private String password;
}