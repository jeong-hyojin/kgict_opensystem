INSERT INTO user_entity (
  user_id, password, username, email, phone, role, is_active, reg_date
) VALUES
('success', '$2a$10$Mc6JofMLsf7tIP3Age4tpuUSyiscjwgvG7tjh2xsTA0Lf7OSbvptG', '안현진', 'success@example.com', '010-1111-2222', 'USER', true, CURRENT_TIMESTAMP),
('admin01', '$2a$10$Mc6JofMLsf7tIP3Age4tpuUSyiscjwgvG7tjh2xsTA0Lf7OSbvptG', '관리자', 'admin@example.com', '010-9999-0000', 'ADMIN', true, CURRENT_TIMESTAMP),
('honggd', '$2a$10$Mc6JofMLsf7tIP3Age4tpuUSyiscjwgvG7tjh2xsTA0Lf7OSbvptG', '홍길동', 'honggd@example.com', '010-1111-2222', 'USER', true, CURRENT_TIMESTAMP);


INSERT INTO board_entity (uuid, title, content, password, user_id, created_at, updated_at)
VALUES ('e7a22ec3-1111-4f8f-bc6f-8b9a8fd2a123', '첫 번째 게시글', '이것은 첫 번째 게시글의 내용입니다.이것은 첫 번째 게시글의 내용입니다.이것은 첫 번째 게시글의 내용입니다.이것은 첫 번째 게시글의 내용입니다.이것은 첫 번째 게시글의 내용입니다.', 'pass1', 'success', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO board_entity (uuid, title, content, password, user_id, created_at, updated_at)
VALUES ('e6a22ec3-1111-4f8f-bc6f-8b9a8fd2a123', '두 번째 게시글', '이것은 두 번째 게시글의 내용입니다.', 'pass2', 'success', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO board_entity (uuid, title, content, password, user_id, created_at, updated_at)
VALUES ('e5a22ec3-1111-4f8f-bc6f-8b9a8fd2a123', '세 번째 게시글', '이것은 세 번째 게시글의 내용입니다.', 'pass3', 'success', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO board_entity (uuid, title, content, password, user_id, created_at, updated_at)
VALUES ('a9d4eae2-3333-4c13-8f45-2b5a8fd2a789', '세 번째 게시글', '세 번째 게시글의 내용입니다.', 'hongpw', 'honggd', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
