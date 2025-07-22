package com.intern.study.board.service;

import com.intern.study.board.domain.BoardEntity;
import com.intern.study.board.domain.BoardRequestDto;
import com.intern.study.board.domain.BoardResponseDto;
import com.intern.study.board.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Slf4j
@Setter
@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public BoardResponseDto writeBoard( BoardRequestDto requestDto) {

        //RequestDTO -> entity 변환
        BoardEntity entity = requestDto.toEntity();

        //Entity 저장(JPA save)
        BoardEntity savedEntity = boardRepository.save(entity);

        //Entity -> ResponseDTO 변환
        BoardResponseDto responseDto = new BoardResponseDto(savedEntity);

        return responseDto;
    }

    @Transactional
    public BoardEntity getBoard(Long id) {

        BoardEntity board = boardRepository.findById(id)
                .orElseThrow( () -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다."));

        return board;
    }

    @Transactional
    public ArrayList<BoardResponseDto> getBoardList() {

        ArrayList<BoardResponseDto> boardList = new ArrayList<>();

        boardRepository.findAll()
                .forEach(board -> {
                    boardList.add(new BoardResponseDto(board));
                });

        return boardList;
    }

    @Transactional
    public BoardResponseDto updateBoard( BoardRequestDto requestDto) {

        //getBoard 호출해 Id에 해당하는 Board 가져오기
        BoardEntity board = getBoard(requestDto.getId());

        //board update
        board.setTitle(requestDto.getTitle());
        board.setContent(requestDto.getContent());
        board.setUpdatedDate(LocalDateTime.now());

        //JPA 저장
        boardRepository.save(board);

        return new BoardResponseDto(board);
    }


    @Transactional
    public void deleteBoard(Long id) {

        //getBoard 호출해 Id에 해당하는 Board 가져오기
        BoardEntity board = getBoard(id);

        //JPA 삭제
        boardRepository.delete(board);
    }

}