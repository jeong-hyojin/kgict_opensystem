package com.intern.study.board.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "게시글 Request")
public class BoardRequestDto {

    @Schema(title = "게시글 UUID", description = "게시글 수정 시 필요")
    private String uuid;
    
    @Schema(title = "제목", maxLength = 255, requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;
    
    @Schema(title = "내용", requiredMode = Schema.RequiredMode.REQUIRED)
    private String content;
    
    @Schema(title = "비밀번호", maxLength = 255, requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

    public BoardEntity toEntity(){

        return BoardEntity.builder()
                .title(this.title)
                .content(this.content)
                .password(this.password)
                .build();
    }
}