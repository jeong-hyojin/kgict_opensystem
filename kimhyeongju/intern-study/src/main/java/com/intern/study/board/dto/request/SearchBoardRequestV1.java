package com.intern.study.board.dto.request;

import lombok.Data;

import java.util.Optional;

@Data
public class SearchBoardRequestV1 {
    private Optional<String> query;

    public SearchBoardRequestV1(Optional<String> query) {
        this.query = query;
    }
}
