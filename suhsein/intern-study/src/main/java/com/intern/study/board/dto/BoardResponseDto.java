package com.intern.study.board.dto;

import com.intern.study.board.domain.Board;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BoardResponseDto {
	String id;
	String title;
	String content;
    String createdAt;
    String updatedAt;
    boolean deleted;

	public static BoardResponseDto from(Board board) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		return new BoardResponseDto(
					  board.getId()
					, board.getTitle()
					, board.getContent()
                    , board.getCreatedAt() != null ? board.getCreatedAt().format(formatter) : null
                    , board.getUpdatedAt() != null ? board.getUpdatedAt().format(formatter) : null
                    , board.isDeleted()
				);
	}
}
