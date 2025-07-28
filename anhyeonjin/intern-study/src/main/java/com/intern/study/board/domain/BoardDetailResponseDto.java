package com.intern.study.board.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@Schema(name = "게시글 상세 Response", description = "게시글의 상세 정보를 반환하는 DTO")
public class BoardDetailResponseDto {

    @Schema(title = "게시글 UUID")
    private String uuid;

    @Schema(title = "제목", maxLength = 255, example = "게시글 제목입니다.")
    private String title;

    @Schema(title = "내용", example = "이 게시글의 내용입니다.")
    private String content;

    @Schema(title = "작성자 ID")
    private String userId;

    @Schema(title = "작성일시")
    private LocalDateTime createdAt;

    @Schema(title = "수정일시")
    private LocalDateTime updatedAt;
}
