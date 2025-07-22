package com.intern.study.board.service;

import com.intern.study.board.domain.BoardEntity;
import com.intern.study.board.dto.response.BoardResponseV1;
import com.intern.study.board.repository.
        BoardRepository;
import com.intern.study.common.Pagination;
import com.intern.study.common.Result;
import com.intern.study.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public Result<BoardEntity> createBoard(
            String userId,
            String title,
            String content,
            String password
    ) {
        return userRepository.findByUserId(userId)
                .map(user -> {
                    // userId 유효하면 board 저장
                    BoardEntity board = BoardEntity.create(user, title, content, password);
                    BoardEntity res = boardRepository.save(board);
                    return Result.success(res);
                })
                .orElseGet(() -> {
                    // userId가 유효하지 않다면, 에러 반환
                    String errorMessage = "유효하지 않은 userId: " + userId;
                    log.error(errorMessage);
                    return Result.fail(errorMessage);
                });
    }

    public Result<BoardEntity> findBoard(
            Long boardId
    ){
        Optional<BoardEntity> boardOpt = boardRepository.findById(boardId);

        String errorMessage = "유효하지 않은 boardId: " + boardId;
        // 유효성 검사 1: 올바른 boardId
        if(boardOpt.isEmpty()) {
            return Result.fail(errorMessage);
        }

        // 유효성 검사 2 : 삭제 유무
        BoardEntity board = boardOpt.get();
        if(board.getIsDeleted()){
            return  Result.fail(errorMessage);
        }

        return Result.success(board);
    }

    public Pagination<BoardResponseV1> searchBoards(
            Optional<String> query,
            int page,
            int size
    ){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        String word = query.filter(q -> !q.isBlank()).orElse(null);

        Page<BoardResponseV1> result = (word != null)
                ? boardRepository.searchBoardsWithQuery(word, pageable)
                : boardRepository.searchBoards(pageable);

        return Pagination.of(
                page,
                size,
                result.getTotalElements(),
                result.getTotalPages(),
                result.getContent()
        );
    }

}
