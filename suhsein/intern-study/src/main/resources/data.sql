INSERT INTO user_entity (
  user_id, password, username, email, phone, role, is_active, reg_date
) VALUES
('abc', '$2a$10$Mc6JofMLsf7tIP3Age4tpuUSyiscjwgvG7tjh2xsTA0Lf7OSbvptG', '서세인', 'abc@example.com', '010-1234-5678', 'USER', true, CURRENT_TIMESTAMP),
('admin01', '$2a$10$Mc6JofMLsf7tIP3Age4tpuUSyiscjwgvG7tjh2xsTA0Lf7OSbvptG', '관리자', 'admin@example.com', '010-9999-0000', 'ADMIN', true, CURRENT_TIMESTAMP),
('honggd', '$2a$10$Mc6JofMLsf7tIP3Age4tpuUSyiscjwgvG7tjh2xsTA0Lf7OSbvptG', '홍길동', 'honggd@example.com', '010-1111-2222', 'USER', true, CURRENT_TIMESTAMP);