
-- USER TABLE
INSERT INTO user_entity (
    user_id, password, username, email, phone, role, is_active, reg_date
) VALUES
--       비밀번호 1111
      ('test', '$2a$10$Mc6JofMLsf7tIP3Age4tpuUSyiscjwgvG7tjh2xsTA0Lf7OSbvptG', '박승현', 'jjack@example.com', '010-9999-0000', 'ADMIN', true, CURRENT_TIMESTAMP),
      ('admin01', '$2a$10$Mc6JofMLsf7tIP3Age4tpuUSyiscjwgvG7tjh2xsTA0Lf7OSbvptG', '관리자', 'admin@example.com', '010-9999-0000', 'ADMIN', true, CURRENT_TIMESTAMP),
      ('honggd', '$2a$10$Mc6JofMLsf7tIP3Age4tpuUSyiscjwgvG7tjh2xsTA0Lf7OSbvptG', '홍길동', 'honggd@example.com', '010-1111-2222', 'USER', true, CURRENT_TIMESTAMP);

-- BOARD TABLE
-- data.sql은 JPA(Hibernate)를 거치지 않고 H2가 직접 SQL을 실행하기에 uuid/createdAt/updatedAt 넣어줘야 함.
INSERT INTO board_entity(
    title, content, password, uuid, created_at, updated_at
) VALUES
      ('아침 메뉴 추천', '샌드위치는 맛있다. 샌드위치는 맛있다. 샌드위치는 맛있다.', '1234','uuid-1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
      ('점심 메뉴 추천', '돈까스는 맛있다. 돈까스는 맛있다. 돈까스는 맛있다.', '1234','uuid-2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
      ('저녁 메뉴 추천', '삼겹살은 맛있다. 삼겹살은 맛있다. 삼겹살은 맛있다.', '1234','uuid-3', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
