package com.intern.study.board.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.intern.study.board.domain.Board;
import com.intern.study.board.dto.BoardRequestDto;
import com.intern.study.board.repository.BoardRepositoryInterface;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BoardService {
	BoardRepositoryInterface boardRepository;
	
	public Map<String, Object> createBoard(BoardRequestDto request) {
		Map<String, Object> body = new HashMap<>();
		Map<String, Object> data = new HashMap<>();
		
		Long id = boardRepository.save(Board.from(request));
		
		data.put("id", id);
		
		body.put("success", true);
		body.put("message", "게시물이 작성되었습니다 ID = " + id);
		body.put("data", data);
		
		return body;
	}
	
	public Map<String, Object> updateBoard(Long id, BoardRequestDto request) {
		Map<String, Object> body = new HashMap<>();
		Map<String, Object> data = new HashMap<>();
	
		Board retrieved = boardRepository.findById(id);
		
		if(retrieved == null) {
			body.put("success", false);
			body.put("message", "존재하지 않는 게시물입니다.");
		} else if(!retrieved.getPassword().equals(request.getPassword())) {
			body.put("success", false);
			body.put("message", "비밀번호가 일치하지 않습니다.");
		} else {
			Board updated = boardRepository.update(id, Board.from(request));
			body.put("success", true);
			body.put("message", "수정된 내용 \n title: " + updated.getTitle() + " \n content : " + updated.getContent());
				
			data.put("prev", Map.of(
							"title", retrieved.getTitle(),
							"content", retrieved.getContent()		
					));
			data.put("updated", Map.of(
							"title", updated.getTitle(),
							"content", updated.getContent()		
					));
		}
		body.put("data", data);
		
		return body;
	}
	
	public Map<String, Object> deleteBoard(Long id) {
		Map<String, Object> body = new HashMap<>();
		Map<String, Object> data = new HashMap<>();
		
		if(boardRepository.findById(id) == null) {
			body.put("success", false);
			body.put("message", "존재하지 않는 게시물입니다.");
		} else {
			body.put("success", true);
			body.put("message", "게시물이 삭제되었습니다. ID = " + id);
		}
		
		data.put("id", id);
		body.put("data", data);
		
		return body;
	}
}
