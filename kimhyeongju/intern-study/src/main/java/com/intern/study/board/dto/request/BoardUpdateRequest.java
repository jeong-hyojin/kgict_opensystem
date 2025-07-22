package com.intern.study.board.dto.request;

import lombok.Data;

@Data
public class BoardUpdateRequest {
    private String boardId;
    private String content;
}
