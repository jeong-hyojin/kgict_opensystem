package com.intern.study.board.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(name = "게시글 Response")
public class BoardResponseDto {

    @Schema(title = "게시글 UUID")
    private String uuid;
    
    @Schema(title = "제목")
    private String title;
    
    @Schema(title = "내용")
    private String content;

    @Schema(title = "생성일시")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;

    @Schema(title = "수정일시")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd HH:mm")
    private LocalDateTime updatedAt;

    public BoardResponseDto(BoardEntity board){
        this.uuid = board.getUuid();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.createdAt = board.getCreatedAt();
        this.updatedAt = board.getUpdatedAt();
    }
}
