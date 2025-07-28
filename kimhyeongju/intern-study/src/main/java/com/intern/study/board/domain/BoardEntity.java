package com.intern.study.board.domain;


import com.intern.study.common.BaseEntity;
import com.intern.study.user.domain.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Table(name = "p_board")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_Id")
    private UserEntity user;

    private String title;
    private String content;
    private String password;
    private Boolean isDeleted;

    @Builder
    private BoardEntity(
            UserEntity user,
            String title,
            String content,
            String password
    ) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.password = password;
        this.isDeleted = Boolean.FALSE;
    }

    public static BoardEntity create(
            UserEntity user,
            String title,
            String content,
            String passoword
    ) {
        return BoardEntity.builder()
                .user(user)
                .title(title)
                .content(content)
                .password(passoword)
                .build();
    }

    public void updateTitle(String title){
        this.title = title;
    }

    public void updateContent(String content){
        this.content = content;
    }

    public void updatePassword(String password){
        this.password = password;
    }

    public void deleteBoard(){
        this.isDeleted = Boolean.TRUE;
    }
}