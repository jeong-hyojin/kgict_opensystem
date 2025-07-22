package com.intern.study.board.controller;

import java.util.List;
import java.util.Map;

import com.intern.study.board.domain.Board;
import com.intern.study.board.dto.BoardUpdateResponseDto;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intern.study.board.dto.BoardRequestDto;
import com.intern.study.board.dto.BoardResponseDto;
import com.intern.study.board.service.BoardService;
import com.intern.study.common.ApiResponse;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequestMapping("/api/board")
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class BoardController {
    BoardService boardService;

    // 전체 게시판 목록 읽기
    @GetMapping
    public ApiResponse<?> getBoardList() {
        List<BoardResponseDto> data = boardService.getBoardList();
        return new ApiResponse<>("SUCCESS", "게시물 리스트 불러오기 성공", data);
    }

    // 게시물 상세 조회
    @GetMapping("/{postId}")
    public ApiResponse<?> getBoardPost(@PathVariable Long postId) {
        Board boardPost = boardService.getBoardPost(postId);
        return new ApiResponse<>("SUCCESS", postId + "번 게시물 불러오기 성공", BoardResponseDto.from(boardPost));
    }

    // 게시물 생성
    @PostMapping("/create")
    public ApiResponse<?> createBoard(@RequestBody BoardRequestDto request) {
        Long id = boardService.createBoard(request);
        return new ApiResponse<>("SUCCESS", "게시물이 작성되었습니다 ID = " + id, Map.of("id", id));
    }

    // 게시물 갱신
    @PutMapping("/{id}")
    public ApiResponse<?> updateBoard(  @PathVariable Long id
                                      , @RequestBody BoardRequestDto request ) {

        BoardUpdateResponseDto data = boardService.updateBoard(id, request);

        if(data == null) {
            return new ApiResponse<>("FAIL", "비밀번호가 일치하지 않습니다.", null);
        }

        BoardResponseDto updated = data.getUpdated();
        String message =  "수정된 내용 \n title: "
                         + updated.getTitle()
                         + "\n content : "
                         + updated.getContent();

        return new ApiResponse<>("SUCCESS", message, data);
    }

    // 게시물 삭제
    @DeleteMapping("/{id}")
    public ApiResponse<?> deleteBoard(@PathVariable Long id) {
        boolean isDeleted = boardService.deleteBoard(id);
        Map<String, Long> data = Map.of("id", id);

        if(!isDeleted) {
            return new ApiResponse<>("FAIL", "존재하지 않는 게시물입니다.", data);
        }
        return new ApiResponse<>("SUCCESS", "게시물이 삭제되었습니다. ID = " + id, data);
    }
}
