package com.intern.study.board.domain;

import com.intern.study.board.dto.BoardRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	String id;
	String title;
	String content;
	String password;
	

	public static Board from(BoardRequestDto board, PasswordEncoder passwordEncoder) {
		return new Board(
			  null
		 	, board.getTitle()
			, board.getContent()
			, passwordEncoder.encode(board.getPassword())
		);
	}
	
	public void update(BoardRequestDto board) {
		this.title   = board.getTitle();
		this.content = board.getContent();
	}
}
