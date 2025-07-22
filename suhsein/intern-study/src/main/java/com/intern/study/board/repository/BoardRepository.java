package com.intern.study.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intern.study.board.domain.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>{

}
