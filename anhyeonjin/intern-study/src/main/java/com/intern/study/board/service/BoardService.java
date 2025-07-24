package com.intern.study.board.service;

import com.intern.study.board.domain.BoardDetailResponseDto;
import com.intern.study.board.domain.BoardEntity;
import com.intern.study.board.domain.BoardListResponseDto;
import com.intern.study.board.repository.BoardRepository;
import com.intern.study.common.domain.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {

    private final BoardRepository boardRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 게시글 작성
     */
    @Transactional
    public ApiResponse<?> writePost(Map<String, String> body) {
        try {
            String title = body.get("title");
            String content = body.get("content");
            String password = body.get("password");
            String userId = body.get("userId");

            String uuid = UUID.randomUUID().toString();

            BoardEntity post = BoardEntity.builder()
                    .uuid(uuid)
                    .title(title)
                    .content(content)
                    .userId(userId)
                    .password(passwordEncoder.encode(password))
                    .build();

            boardRepository.save(post);

            return new ApiResponse<>("SUCCESS", "게시글이 등록되었습니다", uuid);
        } catch (Exception e) {
            log.error("게시글 등록 중 예외 발생", e);
            return new ApiResponse<>("FAIL", "게시글 등록 중 오류가 발생했습니다", null);
        }
    }

    /**
     * 게시글 수정
     */
    @Transactional
    public ApiResponse<?> updatePost(Map<String, String> body) {
        try {
            String uuid = body.get("uuid");
            String content = body.get("content");

            Optional<BoardEntity> postOpt = boardRepository.findByUuid(uuid);

            if (postOpt.isEmpty()) {
                return new ApiResponse<>("FAIL", "수정할 게시글이 존재하지 않습니다.", null);
            }

            BoardEntity post = postOpt.get();
            post.setContent(content);
            post.setUpdatedAt(LocalDateTime.now());
            boardRepository.save(post);

            return new ApiResponse<>("SUCCESS", "게시글이 수정되었습니다", uuid);
        } catch (Exception e) {
            log.error("게시글 수정 중 예외 발생", e);
            return new ApiResponse<>("FAIL", "게시글 수정 중 오류가 발생했습니다", null);
        }
    }

    /**
     * 게시글 삭제
     */
    @Transactional
    public Map<String, Object> deletePost(String uuid) {
        Map<String, Object> result = new HashMap<>();
        Optional<BoardEntity> postOpt = boardRepository.findByUuid(uuid);

        if (postOpt.isEmpty()) {
            result.put("success", false);
            result.put("message", "삭제할 게시글이 존재하지 않습니다.");
            result.put("data", null);
            return result;
        }

        boardRepository.delete(postOpt.get());
        result.put("success", true);
        result.put("message", "게시글이 삭제되었습니다.");
        result.put("data", uuid);
        return result;
    }

    /**
     * 특정 사용자의 게시글 목록 조회
     */
    public ApiResponse<?> getUserPosts(String userId) {
        try {
            List<BoardEntity> posts = boardRepository.findByUserId(userId);

            List<BoardListResponseDto> response = posts.stream()
                    .map(post -> BoardListResponseDto.builder()
                            .uuid(post.getUuid())
                            .title(post.getTitle())
                            .content(post.getContent())
                            .userId(post.getUserId())
                            .createdAt(post.getCreatedAt())
                            .build())
                    .toList();

            return new ApiResponse<>("SUCCESS", "게시글 조회 성공", response);
        } catch (Exception e) {
            log.error("게시글 조회 중 오류 발생", e);
            return new ApiResponse<>("FAIL", "게시글 조회 중 오류 발생", null);
        }
    }

    /**
     * 게시글 상세 조회
     */
    public ApiResponse<?> getPostDetail(String uuid) {
        Optional<BoardEntity> postOpt = boardRepository.findByUuid(uuid);

        if (postOpt.isEmpty()) {
            return new ApiResponse<>("FAIL", "게시글이 존재하지 않습니다.", null);
        }

        BoardEntity post = postOpt.get();
        BoardDetailResponseDto dto = BoardDetailResponseDto.builder()
                .uuid(post.getUuid())
                .title(post.getTitle())
                .content(post.getContent())
                .userId(post.getUserId())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();

        return new ApiResponse<>("SUCCESS", "게시글 조회 성공", dto);
    }

    /**
     * 전체 게시글 목록 조회
     */
    public ApiResponse<?> getAllPosts() {
        List<BoardEntity> posts = boardRepository.findAll();

        List<BoardListResponseDto> data = posts.stream()
                .map(post -> BoardListResponseDto.builder()
                        .uuid(post.getUuid())
                        .title(post.getTitle())
                        .content(post.getContent())
                        .userId(post.getUserId())
                        .createdAt(post.getCreatedAt())
                        .build())
                .toList();

        return new ApiResponse<>("SUCCESS", "전체 게시글 조회 성공", data);
    }
}
