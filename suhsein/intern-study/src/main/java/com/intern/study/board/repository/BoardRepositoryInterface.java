package com.intern.study.board.repository;

import com.intern.study.board.domain.Board;

public interface BoardRepositoryInterface {
	Board  findById(Long id);
	Long   save(Board board);
	Board  update(Long id, Board board);
	void   delete(Long id);
}
