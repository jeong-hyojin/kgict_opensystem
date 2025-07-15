package com.intern.study.board.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intern.study.board.dto.BoardRequestDto;
import com.intern.study.board.service.BoardService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequestMapping("/api/board")
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class BoardController {
	BoardService boardService;
	
	@PostMapping("/create")
	public ResponseEntity<Map<String, Object>> createBoard(@RequestBody BoardRequestDto request) {
		Map<String, Object> body = boardService.createBoard(request);
		
		if(!(Boolean)body.get("success")) {
			return ResponseEntity.badRequest().body(body);
		}
		return ResponseEntity.ok(body);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> updateBoard(
								@PathVariable Long id,
								@RequestBody  BoardRequestDto request
							) {
		Map<String, Object> body = boardService.updateBoard(id, request);
		
		if(!(Boolean)body.get("success")) {
			return ResponseEntity.badRequest().body(body);
		}
		return ResponseEntity.ok(body);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> deleteBoard(@PathVariable Long id) {
		Map<String, Object> body = boardService.deleteBoard(id);
		
		if(!(Boolean)body.get("success")) {
			return ResponseEntity.badRequest().body(body);
		}
		return ResponseEntity.ok(body);
	}
}
