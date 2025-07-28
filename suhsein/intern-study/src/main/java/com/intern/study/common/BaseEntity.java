package com.intern.study.common;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class BaseEntity {
    
    @CreatedDate
    @Column(nullable = false, updatable = false)
    LocalDateTime createdAt; // 등록일
    
    @LastModifiedDate
    @Column(nullable = false)
    LocalDateTime updatedAt; // 수정일
    
    @Column(nullable = false)
    boolean deleted = false; // 소프트삭제 여부
    
    // 소프트삭제 메서드
    public void softDelete() {
        this.deleted = true;
    }
    
    // 소프트삭제 복구 메서드
    public void restore() {
        this.deleted = false;
    }
} 