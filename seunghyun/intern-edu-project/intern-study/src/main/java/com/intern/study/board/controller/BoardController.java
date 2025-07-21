package com.intern.study.board.controller;

import java.awt.dnd.DropTargetAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.intern.study.board.domain.BoardEntity;
import com.intern.study.board.domain.BoardResponseDto;
import com.intern.study.board.repository.BoardRepository;
import com.intern.study.common.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/boards")
public class BoardController {

	private final BoardRepository boardRepository;

	public BoardController(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}

	// 게시글 작성
	@PostMapping("/write")
	public Map<String, Object> writeBoard(@RequestBody Map<String, String> request) {
		
		Map<String, Object> response = new HashMap<>();

	    Map<String, String> data = new HashMap<>();
	    data.put("UUID", request.get("UUID"));
	    data.put("title", request.get("title"));
	    data.put("content", request.get("content"));
	    data.put("password", request.get("password"));

	    response.put("code", "SUCCESS");
	    response.put("message", "게시글 작성이 완료되었습니다. (UUID: " + request.get("UUID") + 
	    											  ", 제목: " + request.get("title") + 
	    											  ", 내용: " + request.get("content") + 
	    											  ", 비밀번호: " + request.get("password") + 
	    											  ")");
	    response.put("data", data);

	    return response;
	}
	
	//게시글 수정
	@PutMapping("/update")
	public Map<String, Object> updateBoard(@RequestBody Map<String, String> request) {
		
		Map<String, Object> response = new HashMap<>();
		
		//DB에서 UUID에 해당하는 게시글 객체를 가져왔다고 가정
		
		//게시글 내용 수정
	    Map<String, String> data = new HashMap<>();
	    data.put("UUID", request.get("newUUID"));
	    data.put("content", request.get("newContent"));

	    response.put("code", "SUCCESS");
	    response.put("message", "게시글이 수정되었습니다. (UUID: " + request.get("newUUID") +
	    											", 내용: " + request.get("newContent") + 
	    											")");
	    response.put("data", data);

	    return response;
	}
	
	
	//게시글 삭제
	@DeleteMapping("/delete")
	public Map<String, Object> deleteBoard( @RequestParam String UUID) {
		
		//서버에서 UUID에 해당하는 게시글 가져왔다고 가정
		
	    Map<String, Object> response = new HashMap<>();

	    response.put("code", "SUCCESS");
	    response.put("message", "게시글 " + UUID + "이 삭제되었습니다.");
	    response.put("data", null);
	    
	    return response;
	}

	//게시글 불러오기
	@GetMapping("board-list")
	public ApiResponse<?> showBoardList() {

		ArrayList<BoardResponseDto> boardList = new ArrayList<>();
		
		boardRepository.findAll().forEach(board -> {
			boardList.add(new BoardResponseDto(board));
		});

		return new ApiResponse<>("SUCCESS", "전체 게시글 목록", boardList);

	}

	//게시글 상세 조회
	@GetMapping("/{id}")
	public ApiResponse<?> showBoard(@PathVariable String id) {

		System.out.println("게시글 상세 조회");
		log.info(id);
		Long boardId = Long.parseLong(id);

		try{
			Optional<BoardEntity> boardOpt = boardRepository.findById(boardId);

			if(boardOpt.isEmpty()) {
				return new ApiResponse<>("FAIL", "해당 게시글을 찾을 수 없습니다.", null);
			}
			BoardResponseDto board = new BoardResponseDto(boardOpt.get());

			return new ApiResponse<>("SUCCESS","조회 성공", board);

		}catch (Exception e) {
			log.error("게시글 조회 중 에러 발생 ",e);
			return new ApiResponse<>("FAIL","조회 처리 중 에러가 발생했습니다.",null);
		}


	}

}
