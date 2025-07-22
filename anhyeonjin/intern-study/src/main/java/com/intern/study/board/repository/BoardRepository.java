package com.intern.study.board.repository;

import java.util.List;
import java.util.Optional;

import com.intern.study.board.domain.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BoardRepository extends JpaRepository<BoardEntity, Long>{
    List<BoardEntity> findByUserId(String userId);
    Optional<BoardEntity> findByUuid(String uuid);
}

