package com.intern.study.user.repository;

import com.intern.study.user.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserId(String userId);

    Optional<UserEntity> findByUsernameAndPassword(String userId, String password);

}

