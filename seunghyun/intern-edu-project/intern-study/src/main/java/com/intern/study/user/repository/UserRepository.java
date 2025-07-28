package com.intern.study.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intern.study.user.domain.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
	
	Optional<UserEntity> findByUserId(String userId);
	
	//로그인용 쿼리 메소드
	Optional<UserEntity> findByUsernameAndPassword(String username, String password);
	
	
}
