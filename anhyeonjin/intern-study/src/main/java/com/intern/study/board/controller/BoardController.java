package com.intern.study.board.controller;

import com.intern.study.board.service.BoardService;
import com.intern.study.common.domain.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

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
@Tag(name = "게시판", description = "게시판 관련 API")
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/write")
    @Operation(summary = "게시글 작성", description = "새로운 게시글을 작성합니다.")
    public ApiResponse<?> writePost(@RequestBody Map<String, String> body) {
        return boardService.writePost(body);
    }

    @PutMapping("/update")
    @Operation(summary = "게시글 수정", description = "기존 게시글의 내용을 수정합니다.")
    public ApiResponse<?> updatePost(@RequestBody Map<String, String> body) {
        return boardService.updatePost(body);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "게시글 삭제", description = "UUID를 기반으로 게시글을 삭제합니다.")
    public Map<String, Object> deletePost(@RequestParam String uuid) {
        return boardService.deletePost(uuid);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "사용자의 게시글 목록 조회", description = "특정 사용자가 작성한 게시글 목록을 조회합니다.")
    public ApiResponse<?> getUserPosts(@PathVariable String userId) {
        return boardService.getUserPosts(userId);
    }

    @GetMapping("/detail/{uuid}")
    @Operation(summary = "게시글 상세 조회", description = "게시글 UUID를 기반으로 상세 내용을 조회합니다.")
    public ApiResponse<?> getPostDetail(@PathVariable String uuid) {
        return boardService.getPostDetail(uuid);
    }

    @GetMapping("/all")
    @Operation(summary = "전체 게시글 목록 조회", description = "모든 사용자가 작성한 전체 게시글 목록을 조회합니다.")
    public ApiResponse<?> getAllPosts() {
        return boardService.getAllPosts();
    }
}
