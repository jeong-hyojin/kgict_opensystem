package com.intern.study.board.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardResponseV1 {
    private final Long boardId;
    private final  Long userId;
    private final  String username;
    private final  String title;
    private final  String content;
    private final LocalDateTime createAt;

    public BoardResponseV1(
            Long boardId,
            Long userId,
            String username,
            String title,
            String content,
            LocalDateTime createAt
    ) {
        this.boardId = boardId;
        this.userId = userId;
        this.username = username;
        this.title = title;
        this.content = content;
        this.createAt = createAt;
    }
}
