package com.intern.study.board.controller;

import com.intern.study.board.domain.BoardEntity;
import com.intern.study.board.dto.request.CreateBoardRequestV1;
import com.intern.study.board.dto.request.SearchBoardRequestV1;
import com.intern.study.board.dto.request.UpdateBoardRequestV1;
import com.intern.study.board.dto.response.BoardResponseV1;
import com.intern.study.board.dto.response.CreateBoardResponseV1;
import com.intern.study.board.service.BoardMapper;
import com.intern.study.board.service.BoardService;
import com.intern.study.common.ApiResponse;
import com.intern.study.common.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/board")
public class BoardControllerV1 {

    private static final boolean ALERT_ENABLED = true;
    private static final boolean ALERT_DISABLED = false;

    private final BoardService boardService;
    private final BoardMapper mapper;


    @PostMapping
    public ApiResponse createBoard(
            @RequestBody CreateBoardRequestV1 request
    ) {
        log.info("[게시글 생성 요청] : " + request.toString());
        Result<BoardEntity> result = boardService.createBoard(
                request.getUserId(),
                request.getTitle(),
                request.getContent(),
                request.getPassword()
        );

        if (!result.isSuccess()) {
            return ApiResponse.fail(result.getMessage());
        }

        CreateBoardResponseV1 response = mapper.toCreateBoardResponse(result.getData());
        return ApiResponse.success(response,ALERT_ENABLED);
    }

    @GetMapping("/{boardId}")
    public ApiResponse findBoard(
            @PathVariable Long boardId
    ) {
        log.info("[게시글 find 요청] boardId: " + boardId);
        Result<BoardEntity> result = boardService.findBoard(boardId);

        if (!result.isSuccess()) {
            return ApiResponse.fail(result.getMessage());
        }

        BoardResponseV1 response = mapper.toBoardResponse(result.getData());
        return ApiResponse.success(response,ALERT_DISABLED);

    }

    @GetMapping
    public ApiResponse searchBoards(
            @RequestParam(defaultValue = "0") final int page,
            @RequestParam(defaultValue = "10") final int size,
            @ModelAttribute SearchBoardRequestV1 request
    ) {
        log.info("[게시글 search 요청] page: " + page + " size: " + size + " query: " + request.toString());
        return ApiResponse.success(boardService.searchBoards(request.getQuery(), page, size),ALERT_DISABLED);
    }

    @PutMapping("/{boardId}")
    public ApiResponse updateBoard(
            @PathVariable Long boardId,
            @RequestBody UpdateBoardRequestV1 request
    ) {
        log.info("[게시글 updatd 요청] : " + request.toString());
        Result<BoardEntity> result = boardService.updateBoard(
                boardId,
                request.getUserId(),
                request.getTitle(),
                request.getContent(),
                request.getPassword()
        );
        BoardResponseV1 response = mapper.toBoardResponse(result.getData());
        return ApiResponse.success(response, ALERT_ENABLED);
    }


}
