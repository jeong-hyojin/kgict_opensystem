package com.intern.study.board.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.intern.study.board.domain.Board;
import com.intern.study.board.dto.BoardRequestDto;
import com.intern.study.board.dto.BoardResponseDto;
import com.intern.study.board.dto.BoardUpdateResponseDto;
import com.intern.study.board.repository.BoardRepository;
import com.intern.study.common.ApiResponse;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BoardService {
//	BoardRepositoryInterface boardRepository; // Map 
	BoardRepository boardRepository; 		  			// JPA
	PasswordEncoder passwordEncoder;

	// 전체 게시판 목록 읽기
	public List<BoardResponseDto> getBoardList() {
		List<Board> boardList = boardRepository.findAll();
		return  boardList
				.stream().map(BoardResponseDto::from)
				.toList();
	}

	// 게시물 상세 조회
	public Board getBoardPost(String postId) {
		Board retrieved = boardRepository.findById(postId)
				.orElseThrow(() -> new IllegalArgumentException("게시물 번호 " + postId + " 게시물이 존재하지 않습니다."));

		return retrieved;
	}

	// 게시물 생성
	public String createBoard(BoardRequestDto request) {
		Board saved = boardRepository.save(Board.from(request, passwordEncoder));
		return saved.getId();
	}
	
	// 게시물 갱신
	public BoardUpdateResponseDto updateBoard(String id, BoardRequestDto request) {
		Board retrieved = boardRepository.findById(id)
										 .orElseThrow(() -> new IllegalAccessError("존재하지 않는 게시물입니다."));
		
		// 비밀번호 일치 확인
		if(!passwordEncoder.matches(request.getPassword(), retrieved.getPassword())) {
			return null;
		}

		// 이전 데이터 DTO로 저장
		BoardResponseDto prev = BoardResponseDto.from(retrieved);

		// 값 수정
		retrieved.update(request);
		boardRepository.save(retrieved);

		// 수정된 데이터 DTO로 저장
		BoardResponseDto 	  updated = BoardResponseDto.from(retrieved);
		BoardUpdateResponseDto data = new BoardUpdateResponseDto(prev, updated);

		return data;
	}

	// 게시물 삭제
	public boolean deleteBoard(String id) {
		// 게시물 존재 여부 확인
		if(!boardRepository.existsById(id)) {
			return false;
		}
		// 게시물 삭제
		boardRepository.deleteById(id);
		return true;
	}
}
