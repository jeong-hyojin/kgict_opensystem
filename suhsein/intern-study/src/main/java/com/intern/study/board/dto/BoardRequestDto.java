package com.intern.study.board.dto;

import com.intern.study.board.domain.Board;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BoardRequestDto {
	String title;
	String content;
	String password;
	
	public static BoardRequestDto from(Board board) {
		return new BoardRequestDto(
		 	  board.getTitle()
			, board.getContent()
			, board.getPassword()
		);
	}
}
