package com.intern.study.board.dto.response;

import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(makeFinal = true)
public class CreateBoardResponseV1 {
    private Long boardId;
    private String userId;
    private String title;
    private String content;

    public CreateBoardResponseV1(
            Long boardId,
            String userId,
            String title,
            String content
    ) {
        this.boardId = boardId;
        this.userId = userId;
        this.title = title;
        this.content = content;
    }
}
