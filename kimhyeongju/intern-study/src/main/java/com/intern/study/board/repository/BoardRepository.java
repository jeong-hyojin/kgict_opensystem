package com.intern.study.board.repository;

import com.intern.study.board.domain.BoardEntity;
import com.intern.study.board.dto.response.BoardResponseV1;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<BoardEntity,Long> {
    @Query("""
        SELECT new com.intern.study.board.dto.response.BoardResponseV1(
            b.id, 
            u.userId,
            u.username,
            b.title, 
            b.content,
            b.createdAt
        )
        FROM BoardEntity b
        JOIN b.user u 
        WHERE b.isDeleted = false
    """)
    Page<BoardResponseV1> searchBoards(Pageable pageable);

    @Query("""
        SELECT new com.intern.study.board.dto.response.BoardResponseV1(
            b.id, 
            u.userId,
            u.username,
            b.title, 
            b.content,
            b.createdAt
        )
        FROM BoardEntity b
        JOIN b.user u 
        WHERE b.isDeleted = false
        AND (b.title LIKE %:query% OR b.content LIKE %:query%)
    """)
    Page<BoardResponseV1> searchBoardsWithQuery(@Param("query") String query, Pageable pageable);
}
