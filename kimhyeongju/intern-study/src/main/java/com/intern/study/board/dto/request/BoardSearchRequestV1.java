package com.intern.study.board.dto.request;

import lombok.Data;

import java.util.Optional;

@Data
public class BoardSearchRequestV1 {
    private Optional<String> query;

    public BoardSearchRequestV1(Optional<String> query) {
        this.query = query;
    }
}
