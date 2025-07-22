package com.intern.study.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intern.study.user.domain.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
  Optional<UserEntity> findByUserId(String userId);

}
