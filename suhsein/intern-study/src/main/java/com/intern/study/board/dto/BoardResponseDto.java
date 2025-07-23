package com.intern.study.board.dto;

import com.intern.study.board.domain.Board;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BoardResponseDto {
	String id;
	String title;
	String content;

	public static BoardResponseDto from(Board board) {
		return new BoardResponseDto(
					  board.getId()
					, board.getTitle()
					, board.getContent()
				);
	}
}
