package com.intern.study.board.controller;

import com.intern.study.board.dto.BoardCreateRequest;
import com.intern.study.board.dto.BoardUpdateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@RequestMapping("/board")
public class BoardController{

	private static Set<String> repository = new HashSet<>();

	@PostMapping
	public ResponseEntity<Map<String, Object>> createBoard(
		@RequestBody BoardCreateRequest request
	) {
		log.info("[게시물 생성 요청] title: " + request.getTitle() + " content: " + request.getContent() + " password: " + request.getPassword());
		String uuid = UUID.randomUUID().toString();
		repository.add(uuid);

		// 현재 저장된 id 조회
		log.info("현재 게시물 UUID");
		for(String id :repository) {
			log.info(id);
		}

		Map<String, Object> body = new HashMap<>();
	    body.put("success", true);
	    body.put("message", "게시물이 등록되었습니다.(id:" + uuid +")");

	    log.info("게시물 생성 성공");
	    return ResponseEntity.ok(body);
	}

	@PutMapping
	public ResponseEntity<Map<String, Object>> updateBoard(
		@RequestBody BoardUpdateRequest request
	) {
		log.info("[게시물 수정 요청] boardId: " + request.getBoardId() + " content: " + request.getContent());

		// 게시글 수정

		Map<String, Object> body = new HashMap<>();

		String uuid = request.getBoardId();
		if(repository.contains(uuid)) {
			body.put("success", true);
		    body.put("message", "게시물이 수정되었습니다.");
		}else {
			body.put("success", false);
		    body.put("message", "존재하지 않은 게시물입니다.");
		}
	    return ResponseEntity.ok(body);
	}

	@DeleteMapping
	public ResponseEntity<Map<String, Object>> deleteBoard(
		@RequestParam String boardId
	) {
		log.info("[게시물 삭제 요청] boardId: " + boardId);

		// 게시글 삭제

		Map<String, Object> body = new HashMap<>();

		if(repository.contains(boardId)) {
			body.put("success", true);
		    body.put("message", "게시물이 삭제되었습니다.");
		    return ResponseEntity.ok(body);
		}else {
			body.put("success", false);
		    body.put("message", "존재하지 않은 게시물입니다.");
//		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
		    return ResponseEntity.ok(body);
		}
	}
}
