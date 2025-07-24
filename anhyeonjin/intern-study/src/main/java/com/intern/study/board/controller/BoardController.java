package com.intern.study.board.controller;

import com.intern.study.board.service.BoardService;
import com.intern.study.common.domain.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.*;

/**
 * 게시판 관련 요청을 처리한다.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/board")
@CrossOrigin(origins = "*")
public class BoardController {

    private final BoardService boardService;

    // 게시글 작성
    @PostMapping("/write")
    public ApiResponse<?> writePost(@RequestBody Map<String, String> body) {
        return boardService.writePost(body);
    }

    // 게시글 수정
    @PutMapping("/update")
    public ApiResponse<?> updatePost(@RequestBody Map<String, String> body) {
        return boardService.updatePost(body);
    }

    // 게시글 삭제
    @DeleteMapping("/delete")
    public Map<String, Object> deletePost(@RequestParam String uuid) {
        return boardService.deletePost(uuid);
    }

    // 특정 사용자의 게시글 목록 조회
    @GetMapping("/user/{userId}")
    public ApiResponse<?> getUserPosts(@PathVariable String userId) {
        return boardService.getUserPosts(userId);
    }

    // 게시글 상세 정보 조회
    @GetMapping("/detail/{uuid}")
    public ApiResponse<?> getPostDetail(@PathVariable String uuid) {
        return boardService.getPostDetail(uuid);
    }

    // 전체 게시글 목록 조회
    @GetMapping("/all")
    public ApiResponse<?> getAllPosts() {
        return boardService.getAllPosts();
    }
}
