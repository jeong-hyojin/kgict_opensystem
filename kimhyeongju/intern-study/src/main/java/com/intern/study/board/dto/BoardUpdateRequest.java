package com.intern.study.board.dto;

import lombok.Data;

@Data
public class BoardUpdateRequest {
    private String boardId;
    private String content;
}
