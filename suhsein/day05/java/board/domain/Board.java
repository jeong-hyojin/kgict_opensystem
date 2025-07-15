package com.intern.study.board.domain;

import com.intern.study.board.dto.BoardRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Board {
	@Id
	Long id;
	String title;
	String content;
	String password;
	

	public static Board from(BoardRequestDto board) {
		return new Board(
			  null
		 	, board.getTitle()
			, board.getContent()
			, board.getPassword()
		);
	}
}
