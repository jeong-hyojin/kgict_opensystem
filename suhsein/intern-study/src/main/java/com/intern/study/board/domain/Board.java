package com.intern.study.board.domain;

import com.intern.study.board.dto.BoardRequestDto;
import com.intern.study.common.BaseEntity;

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
public class Board extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	String id;
	String title;
	String content;
	String password;
	

	public static Board from(BoardRequestDto board, PasswordEncoder passwordEncoder) {
		Board entity = new Board(
			  null
		 	, board.getTitle()
			, board.getContent()
			, passwordEncoder.encode(board.getPassword())
		);
		// createdAt, updatedAt은 BaseEntity에서 자동 처리
		return entity;
	}
	
	public void update(BoardRequestDto board) {
		this.title   = board.getTitle();
		this.content = board.getContent();
		// updatedAt은 BaseEntity에서 자동 처리
	}

	public void delete() {
		this.softDelete();
	}
}
