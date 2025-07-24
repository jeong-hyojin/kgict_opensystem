package com.intern.study.board.dto.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UpdateBoardRequestV1 {
    private String userId;
    private String title;
    private String content;
    private String password;
}
