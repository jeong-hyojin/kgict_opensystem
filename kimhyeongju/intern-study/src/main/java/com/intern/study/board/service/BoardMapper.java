package com.intern.study.board.service;

import com.intern.study.board.domain.BoardEntity;
import com.intern.study.board.dto.response.BoardResponseV1;
import com.intern.study.board.dto.response.CreateBoardResponseV1;
import org.springframework.stereotype.Component;

@Component
public class BoardMapper {
    public CreateBoardResponseV1 toCreateBoardResponse(BoardEntity entity){
        return new CreateBoardResponseV1(
                entity.getId(),
                entity.getUser().getUserId(),
                entity.getTitle(),
                entity.getContent()
        );
    }

    public BoardResponseV1 toBoardResponse(BoardEntity entity){
        return new BoardResponseV1(
                entity.getId(),
                entity.getUser().getUserId(),
                entity.getUser().getUsername(),
                entity.getTitle(),
                entity.getContent(),
                entity.getCreatedAt()
        );
    }
}
