package com.intern.study.board.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.intern.study.board.domain.Board;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MapBoardRepository implements BoardRepositoryInterface {
	Map<String, Board> storage = new HashMap<>();
	
	@Override
	public Board findById(String id) {
		return storage.get(id);
	}

	@Override
	public String save(Board board) {
		String id = UUID.randomUUID().toString();
		storage.put(id, board);
		return id;
	}

	@Override
	public Board update(String id, Board board) {
		storage.put(id, board);
		return storage.get(id);
	}

	@Override
	public void delete(String id) {
		storage.remove(id);		
	}
	
}
