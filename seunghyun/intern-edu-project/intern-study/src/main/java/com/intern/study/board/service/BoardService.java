package com.intern.study.board.service;

import com.intern.study.board.domain.BoardEntity;
import com.intern.study.board.domain.BoardRequestDto;
import com.intern.study.board.domain.BoardResponseDto;
import com.intern.study.board.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    /**
     * 내부 재사용 용도의 함수
     */
    @Transactional
    public BoardEntity getBoardByUuid(String uuid) {

        return  boardRepository.findByUuid(uuid)
                .orElseThrow( () -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다."));
    }

    /**
     * 게시글 작성
     */
    @Transactional
    public BoardResponseDto writeBoard( BoardRequestDto requestDto) {

        //RequestDTO -> entity 변환
        BoardEntity entity = requestDto.toEntity();

        //Entity 저장(JPA save)
        BoardEntity savedEntity = boardRepository.save(entity);

        //Entity -> ResponseDTO 변환
        return  new BoardResponseDto(savedEntity);
    }

    /**
     * 게시글 조회
     */
    @Transactional
    public BoardResponseDto getBoard(String uuid) {
        return new BoardResponseDto(getBoardByUuid(uuid));
    }

    /**
     * 게시글 리스트 조회
     */
    @Transactional
    public ArrayList<BoardResponseDto> getBoardList() {

        ArrayList<BoardResponseDto> boardList = new ArrayList<>();

        boardRepository.findAll()
                       .forEach(board -> {
                            boardList.add(new BoardResponseDto(board));
                       });

        return boardList;
    }

    /**
     * 게시글 수정
     */
    @Transactional
    public BoardResponseDto updateBoard(BoardRequestDto requestDto) {

        BoardEntity board = getBoardByUuid(requestDto.getUuid());

        //board update
        board.setTitle(requestDto.getTitle());
        board.setContent(requestDto.getContent());

        //JPA Persistence Context로 생략 가능
        boardRepository.save(board);

        return new BoardResponseDto(board);
    }

    /**
     * 게시글 삭제
     */
    @Transactional
    public void deleteBoard(String uuid) {

        BoardEntity board = getBoardByUuid(uuid);
        boardRepository.delete(board);
    }

}