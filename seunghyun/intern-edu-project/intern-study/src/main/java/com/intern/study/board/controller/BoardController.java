package com.intern.study.board.controller;

import java.util.ArrayList;

import com.intern.study.board.domain.BoardRequestDto;
import com.intern.study.board.domain.BoardResponseDto;
import com.intern.study.board.service.BoardService;
import com.intern.study.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@Tag(name = "게시판")
@RequestMapping("/api/boards")
public class BoardController {

	private final BoardService boardService;

	@PostMapping("/write")
	@Operation(summary = "게시글 작성")
	public ApiResponse<?> writeBoard(@RequestBody BoardRequestDto boardRequestDto) {

		try {
			BoardResponseDto responseDto = boardService.writeBoard(boardRequestDto);
			return new ApiResponse<>("SUCCESS","게시글 작성 성공", responseDto.getUuid());

		} catch (Exception e) {
			log.error("회원가입 오류", e);
			return new ApiResponse<>("FAIL", "게시글 작성 중 에러가 발생했습니다.", null);
		}
	}


	@GetMapping("/{uuid}")
	@Operation(summary = "게시글 조회")
	public ApiResponse<?> getBoard(@PathVariable String uuid) {

		try{
			BoardResponseDto responseDto = boardService.getBoard(uuid);
			return new ApiResponse<>("SUCCESS","조회 성공", responseDto);

		} catch(IllegalArgumentException e ){
			return new ApiResponse<>("FAIL", e.getMessage(), null );

		}catch (Exception e) {
			log.error("게시글 조회 중 에러 발생 ",e);
			return new ApiResponse<>("FAIL","조회 중 에러가 발생했습니다.",null);
		}
	}


	@GetMapping("board-list")
	@Operation(summary = "게시글 목록 조회")
	public ApiResponse<?> showBoardList() {

		try {
			ArrayList<BoardResponseDto> boardList = boardService.getBoardList();
			return new ApiResponse<>("SUCCESS", "전체 게시글 목록", boardList);

		}catch (Exception e) {
			log.error("게시글 전체 조회 중 에러 발생 ",e);
			return new ApiResponse<>("FAIL","게시글 전체 조회 중 에러가 발생했습니다.",null);

		}
	}



	@PutMapping("/update")
	@Operation(summary = "게시글 수정")
	public ApiResponse<?> updateBoard(@RequestBody BoardRequestDto boardRequestDto) {

		try{
			BoardResponseDto responseDto = boardService.updateBoard(boardRequestDto);
			return new ApiResponse<>("SUCCESS","게시글 수정 성공", responseDto);

		} catch(IllegalArgumentException e ){
			return new ApiResponse<>("FAIL", e.getMessage(), null );

		}catch (Exception e) {
			log.error("게시글 수정 중 에러 발생 ",e);
			return new ApiResponse<>("FAIL","게시글 수정 중 에러가 발생했습니다.",null);
		}
	}


	@DeleteMapping("/delete/{uuid}")
	@Operation(summary = "게시글 삭제")
	public ApiResponse<?> deleteBoard( @PathVariable String uuid) {

		try{
			boardService.deleteBoard(uuid);
			return new ApiResponse<>("SUCCESS","게시글 삭제 성공", null);

		}catch (Exception e) {
			log.error("게시글 삭제 중 에러 발생 ",e);
			return new ApiResponse<>("FAIL","게시글 삭제 중 에러가 발생했습니다.",null);
		}
	}

}