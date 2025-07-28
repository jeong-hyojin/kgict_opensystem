INSERT INTO p_user (
    user_id, password, username, email, phone, role, is_active, reg_date
) VALUES
      ('honggd', '$2a$10$Mc6JofMLsf7tIP3Age4tpuUSyiscjwgvG7tjh2xsTA0Lf7OSbvptG', '홍길동', 'honggd@example.com', '010-1111-2222', 'USER', true, CURRENT_TIMESTAMP),
      ('test1', '$2a$10$Mc6JofMLsf7tIP3Age4tpuUSyiscjwgvG7tjh2xsTA0Lf7OSbvptG', 'testUser1', 'test1@example.com', '010-1111-2222', 'USER', true, CURRENT_TIMESTAMP),
      ('test2', '$2a$10$Mc6JofMLsf7tIP3Age4tpuUSyiscjwgvG7tjh2xsTA0Lf7OSbvptG', 'testUser2', 'test2@example.com', '010-1111-2222', 'USER', true, CURRENT_TIMESTAMP),
      ('test3', '$2a$10$Mc6JofMLsf7tIP3Age4tpuUSyiscjwgvG7tjh2xsTA0Lf7OSbvptG', 'testUser3', 'test3@example.com', '010-1111-2222', 'USER', true, CURRENT_TIMESTAMP),
      ('admin01', '$2a$10$Mc6JofMLsf7tIP3Age4tpuUSyiscjwgvG7tjh2xsTA0Lf7OSbvptG', '관리자', 'admin@example.com', '010-9999-0000', 'ADMIN', true, CURRENT_TIMESTAMP);


INSERT INTO p_board (user_id, title, content, password, is_deleted, created_at, updated_at
) VALUES
    (3, '공지사항 안내드립니다.', '시스템 점검 예정입니다.', '1234', false, DATEADD('DAY', 0, CURRENT_TIMESTAMP), DATEADD('DAY', 0, CURRENT_TIMESTAMP)),
    (4, '오늘 회원가입했습니다. 반갑습니다 :)', '처음 써보는 게시글입니다.', '1234', false, DATEADD('DAY', -1, CURRENT_TIMESTAMP), DATEADD('DAY', -1, CURRENT_TIMESTAMP)),
    (5, '요새 자유 주제 대해 고민이 있어요', '자유롭게 이야기해요!', '1234', false, DATEADD('DAY', -2, CURRENT_TIMESTAMP), DATEADD('DAY', -2, CURRENT_TIMESTAMP)),
    (4, '개발 공부 개발 개발', 'Spring Boot 정말 재밌네요.', '1234', false, DATEADD('DAY', -3, CURRENT_TIMESTAMP), DATEADD('DAY', -3, CURRENT_TIMESTAMP)),
    (5, '일상 공유 ~~@@~@', '오늘 날씨 정말 좋아요.', '1234', false, DATEADD('DAY', -5, CURRENT_TIMESTAMP), DATEADD('DAY', -5, CURRENT_TIMESTAMP)),
    (3, '@@긴급 공지 사항@@', '내일 서버 재시작합니다.', '1234', false, DATEADD('DAY', -6, CURRENT_TIMESTAMP), DATEADD('DAY', -6, CURRENT_TIMESTAMP)),
    (4, 'QnA 질문', 'JPA에서 N+1 문제 해결법이 뭘까요?', '1234', false, DATEADD('DAY', -8, CURRENT_TIMESTAMP), DATEADD('DAY', -8, CURRENT_TIMESTAMP)),
    (5, '정보 공유', '좋은 블로그 글 발견했어요.', '1234', false, DATEADD('DAY', -9, CURRENT_TIMESTAMP), DATEADD('DAY', -9, CURRENT_TIMESTAMP)),
    (4, '스터디 모집합니다!!', '같이 공부할 사람 구해요.', '1234', false, DATEADD('DAY', -10, CURRENT_TIMESTAMP), DATEADD('DAY', -10, CURRENT_TIMESTAMP)),
    (3, '새로운 기능 출시!!!!!', '댓글 기능이 추가되었습니다.', '1234', false, DATEADD('DAY', -11, CURRENT_TIMESTAMP), DATEADD('DAY', -11, CURRENT_TIMESTAMP)),
    (3, '코드 리뷰 요청합니다', '코드 봐주실 분 계신가요?', '1234', false, DATEADD('DAY', -12, CURRENT_TIMESTAMP), DATEADD('DAY', -12, CURRENT_TIMESTAMP)),
    (4, '잡담잡담잡담잡담잡담잡담', '요즘 뭐 하면서 지내세요?', '1234', false, DATEADD('DAY', -13, CURRENT_TIMESTAMP), DATEADD('DAY', -13, CURRENT_TIMESTAMP)),
    (5, '중요 팁 모읍집.txt', 'git revert vs reset 차이', '1234', false, DATEADD('DAY', -14, CURRENT_TIMESTAMP), DATEADD('DAY', -14, CURRENT_TIMESTAMP)),
    (3, '유의사항 제발 참고', '게시글 작성 시 주의해주세요.', '1234', false, DATEADD('DAY', -4, CURRENT_TIMESTAMP), DATEADD('DAY', -4, CURRENT_TIMESTAMP)),
    (4, '마무리 글~', '오늘도 수고 많으셨어요.', '1234', false, DATEADD('DAY', -7, CURRENT_TIMESTAMP), DATEADD('DAY', -7, CURRENT_TIMESTAMP));
