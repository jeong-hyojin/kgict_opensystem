
-- USER TABLE
INSERT INTO user_entity (
    user_id, password, username, email, phone, role, is_active, reg_date
) VALUES
--       비밀번호 1111
      ('test', '$2a$10$Mc6JofMLsf7tIP3Age4tpuUSyiscjwgvG7tjh2xsTA0Lf7OSbvptG', '박승현', 'jjack@example.com', '010-9999-0000', 'ADMIN', true, CURRENT_TIMESTAMP),
      ('admin01', '$2a$10$Mc6JofMLsf7tIP3Age4tpuUSyiscjwgvG7tjh2xsTA0Lf7OSbvptG', '관리자', 'admin@example.com', '010-9999-0000', 'ADMIN', true, CURRENT_TIMESTAMP),
      ('honggd', '$2a$10$Mc6JofMLsf7tIP3Age4tpuUSyiscjwgvG7tjh2xsTA0Lf7OSbvptG', '홍길동', 'honggd@example.com', '010-1111-2222', 'USER', true, CURRENT_TIMESTAMP);

-- BOARD TABLE
INSERT INTO board_entity(
    UUID, title, content, password, created_date
) VALUES
      (111, '아침 메뉴 추천', '샌드위치는 맛있다.', '1234',CURRENT_TIMESTAMP),
      (222, '점심 메뉴 추천', '돈까스는 맛있다.', '1234',CURRENT_TIMESTAMP),
      (333, '저녁 메뉴 추천', '삼겹살은 맛있다.', '1234',CURRENT_TIMESTAMP);

