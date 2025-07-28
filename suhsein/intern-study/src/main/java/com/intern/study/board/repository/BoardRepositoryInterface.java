package com.intern.study.board.repository;

import com.intern.study.board.domain.Board;

public interface BoardRepositoryInterface {
	Board  findById(String id);
	String   save(Board board);
	Board  update(String id, Board board);
	void   delete(String id);
}
