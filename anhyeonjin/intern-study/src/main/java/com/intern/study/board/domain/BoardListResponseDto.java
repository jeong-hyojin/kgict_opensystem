package com.intern.study.board.domain;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardListResponseDto {
    private String uuid;
    private String title;
    private String content;
    private String userId;
    private LocalDateTime createdAt;
}
