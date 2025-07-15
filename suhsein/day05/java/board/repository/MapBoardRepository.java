package com.intern.study.board.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.intern.study.board.domain.Board;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MapBoardRepository implements BoardRepositoryInterface {
	Map<Long, Board> storage = new HashMap<>();
	Long increment = 0L;
	
	@Override
	public Board findById(Long id) {
		return storage.get(id);
	}

	@Override
	public Long save(Board board) {
		storage.put(++increment, board);
		return increment;
	}

	@Override
	public Board update(Long id, Board board) {
		storage.put(id, board);
		return storage.get(id);
	}

	@Override
	public void delete(Long id) {
		storage.remove(id);		
	}
	
}
